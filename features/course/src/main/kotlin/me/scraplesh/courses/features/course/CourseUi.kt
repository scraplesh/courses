package me.scraplesh.courses.features.course

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.course.databinding.FragmentCourseBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.appcompat.itemClicks
import ru.ldralighieri.corbind.appcompat.navigationClicks
import ru.ldralighieri.corbind.view.clicks

@SuppressLint("SetJavaScriptEnabled")
class CourseUi : Ui<CourseUi.Reaction, CourseUi.UiState, FragmentCourseBinding>() {
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

    fun onBackPress(): Boolean {
        return webView.takeIf { it.copyBackForwardList().currentIndex > 0 }
            ?.let {
                it.goBack()
                true
            }
            ?: false
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
