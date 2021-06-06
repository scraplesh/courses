package ru.emba.cbs.features.course

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import ru.emba.cbs.common.didSet
import ru.emba.cbs.features.course.databinding.FragmentCourseBinding
import ru.emba.cbs.mvi.Ui
import ru.ldralighieri.corbind.appcompat.itemClicks
import ru.ldralighieri.corbind.appcompat.navigationClicks
import ru.ldralighieri.corbind.view.clicks
import javax.inject.Inject

@SuppressLint("SetJavaScriptEnabled")
class CourseUi @Inject constructor() : Ui<CourseUi.Reaction, CourseUi.UiState, FragmentCourseBinding>() {
    enum class Reaction {
        BackClicked,
        PageStoppedLoading,
        RefreshClicked,
        TimeManagementClicked
    }

    sealed class UiState {
        class Loading(val courseUrl: String) : UiState()
        object Error : UiState()
        object Content : UiState()
    }

    private var toolbar: MaterialToolbar by didSet {
        navigationClicks().react { Reaction.BackClicked }

        itemClicks().mapNotNull { item ->
            when (item.itemId) {
                R.id.course_time_management -> Reaction.TimeManagementClicked
                else -> null
            }
        }
            .react { it }
    }
    private var webView: WebView by didSet {
        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                react(Reaction.PageStoppedLoading)
            }
        }

        settings.apply {
            domStorageEnabled = true
            javaScriptEnabled = true
        }

        states.subscribe { state ->
            when (state) {
                is UiState.Loading -> {
                    isVisible = false
                    loadUrl(state.courseUrl)
                }
                UiState.Error -> isVisible = false
                UiState.Content -> isVisible = true
            }
        }
    }
    private var loadingView: View by didSet {
        states.map { it is UiState.Loading }
            .distinctUntilChanged()
            .subscribe { isVisible = it }
    }
    private var errorView: View by didSet {
        states.map { it is UiState.Error }
            .distinctUntilChanged()
            .subscribe { isVisible = it }
    }
    private var refreshButton: View by didSet {
        clicks().react { Reaction.RefreshClicked }
    }

    override fun bindViews(view: FragmentCourseBinding) {
        with(view) {
            toolbar = toolbarCourse
            webView = webviewCourse
            loadingView = progressbarCourse
            errorView = layoutNoNetwork.root
            refreshButton = layoutNoNetwork.buttonRefresh
        }
    }
}
