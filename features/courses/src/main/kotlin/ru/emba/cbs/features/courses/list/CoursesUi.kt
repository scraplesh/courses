package ru.emba.cbs.features.courses.list

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.coroutines.flow.map
import ru.emba.cbs.common.didSet
import ru.emba.cbs.features.courses.databinding.FragmentCoursesBinding
import ru.emba.cbs.features.courses.databinding.ItemCourseBinding
import ru.emba.cbs.mvi.Ui
import javax.inject.Inject

class CoursesUi @Inject constructor() :
    Ui<CoursesUi.Reaction, CoursesUi.UiState, FragmentCoursesBinding>() {

    sealed class Reaction {
        class CourseClicked(val course: ru.emba.cbs.domain.model.Course) : Reaction()
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

    private fun createCourseDelegate(): AdapterDelegate<List<Item>> {
        return adapterDelegateViewBinding(
            { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) }
        ) {
            binding.root.setOnClickListener { react(Reaction.CourseClicked(item.course)) }
            bind {
            }
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = true
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.course.id == newItem.course.id
    }

    class Item(val course: ru.emba.cbs.domain.model.Course)
}