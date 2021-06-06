package ru.emba.cbs.features.timemanagement

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.emba.cbs.common.didSet
import ru.emba.cbs.features.timemanagement.databinding.FragmentTimemanagementBinding
import ru.emba.cbs.features.timemanagement.databinding.ItemTimemanagementChapterBinding
import ru.emba.cbs.features.timemanagement.databinding.ItemTimemanagementSubchapterBinding
import ru.emba.cbs.mvi.Ui
import ru.ldralighieri.corbind.view.clicks

class TimeManagementUi : Ui<
        TimeManagementUi.Reaction,
        TimeManagementUi.UiState,
        FragmentTimemanagementBinding
        >() {
    sealed interface Reaction {
        object CloseClicked : Reaction
        class ChapterClicked(val chapter: ru.emba.cbs.domain.model.Chapter) : Reaction
    }

    class UiState(val items: List<Item>, val progress: Int)

    private var progressView: ProgressBar by didSet {
        states.map { it.progress }
            .distinctUntilChanged()
            .subscribe { progress = it }
    }
    private var closeButton: View by didSet {
        clicks().react { Reaction.CloseClicked }
    }
    private var chaptersList: RecyclerView by didSet {
        adapter = ListDelegationAdapter(
            createChapterDelegate(),
            createSubchapterDelegate()
        )
    }

    override fun bindViews(view: FragmentTimemanagementBinding) {
        with(view) {
            closeButton = imagebuttonTimemanagementClose
            progressView = progressbarOnboardingFirst
            chaptersList = recyclerviewTimemanagement
        }
    }

    private fun createChapterDelegate(): AdapterDelegate<List<Item>> {
        return adapterDelegateViewBinding<Item.ChapterItem, Item, ItemTimemanagementChapterBinding>(
            { inflater, parent ->
                ItemTimemanagementChapterBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            }
        ) {
            binding.root.setOnClickListener { react(Reaction.ChapterClicked(item.chapter)) }

            bind {
                with(binding.textviewItemchapter) {
                    with(item.chapter) {
                        text = title
                        setCompoundDrawablesWithIntrinsicBounds(
                            when (state) {
                                ru.emba.cbs.domain.common.ChapterState.Unread -> R.drawable.ic_dot
                                ru.emba.cbs.domain.common.ChapterState.Completed -> R.drawable.ic_check_mark
                                ru.emba.cbs.domain.common.ChapterState.Finished -> 0
                            },
                            0,
                            when (item.state) {
                                ChapterCollapseState.Collapsed -> R.drawable.ic_tick_down
                                ChapterCollapseState.Expanded -> R.drawable.ic_tick_up
                                ChapterCollapseState.None -> 0
                            },
                            0
                        )
                    }
                }
            }
        }
    }

    private fun createSubchapterDelegate(): AdapterDelegate<List<Item>> {
        return adapterDelegateViewBinding<
                Item.SubchapterItem,
                Item,
                ItemTimemanagementSubchapterBinding
                >(
            { inflater, parent ->
                ItemTimemanagementSubchapterBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            }
        ) {
            binding.root.setOnClickListener { react(Reaction.ChapterClicked(item.chapter)) }

            bind {
                with(binding.textviewItemsubchapter) {
                    with(item.chapter) {
                        text = title
                        setCompoundDrawablesWithIntrinsicBounds(
                            when (state) {
                                ru.emba.cbs.domain.common.ChapterState.Unread -> R.drawable.ic_dot
                                ru.emba.cbs.domain.common.ChapterState.Completed -> R.drawable.ic_check_mark
                                ru.emba.cbs.domain.common.ChapterState.Finished -> 0
                            },
                            0,
                            0,
                            0
                        )
                    }
                }
            }
        }
    }

    sealed class Item(val chapter: ru.emba.cbs.domain.model.Chapter) {
        class ChapterItem(chapter: ru.emba.cbs.domain.model.Chapter, val state: ChapterCollapseState) : Item(chapter)
        class SubchapterItem(chapter: ru.emba.cbs.domain.model.Chapter) : Item(chapter)
    }
}