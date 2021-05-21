package me.scraplesh.courses.features.courses.list

import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.coroutines.flow.map
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.features.courses.databinding.FragmentCoursesBinding
import me.scraplesh.courses.features.courses.databinding.ItemCourseBinding
import me.scraplesh.courses.mvi.Ui

class CoursesUi :
    Ui<CoursesUi.Interaction, CoursesUi.UiState, FragmentCoursesBinding>(),
    DefaultLifecycleObserver {

    sealed class Interaction {
        class CourseClicked(val course: Course) : Interaction()
    }

    sealed class UiState {
        object Loading : UiState()
        object Empty : UiState()
        class Content(val items: List<Item>) : UiState()
    }

    private lateinit var loadingView: View
    private lateinit var emptyView: View
    private lateinit var contentView: View
    private var coursesList: RecyclerView by didSet {
        adapter = AsyncListDifferDelegationAdapter(
            ItemCallback(),
            createCourseDelegate()
        ).apply {
            states.map { state -> (state as? UiState.Content)?.items ?: emptyList() }
                .subscribe { items = it }
        }
    }

    override fun bindViews(view: FragmentCoursesBinding) {
        with(view) {
            loadingView = layoutCoursesLoading.progressbarCoursesLoading
            with(layoutCoursesEmpty) {
                emptyView = layoutCoursesEmpty
            }
            with(layoutCoursesContent) {
                contentView = constraintlayoutCoursesContent
                coursesList = recyclerviewCourses
            }
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        // TODO("Trigger loading")
    }

    private fun createCourseDelegate(): AdapterDelegate<List<Item>> {
        return adapterDelegateViewBinding(
            { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) }
        ) {
            bind {
            }
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = true
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id
    }

    class Item(val id: Int)
}