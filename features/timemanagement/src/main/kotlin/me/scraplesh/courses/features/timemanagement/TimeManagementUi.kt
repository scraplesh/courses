package me.scraplesh.courses.features.timemanagement

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.domain.common.ChapterState
import me.scraplesh.courses.domain.model.Chapter
import me.scraplesh.courses.features.timemanagement.databinding.FragmentTimemanagementBinding
import me.scraplesh.courses.features.timemanagement.databinding.ItemTimemanagementChapterBinding
import me.scraplesh.courses.features.timemanagement.databinding.ItemTimemanagementSubchapterBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.view.clicks

class TimeManagementUi : Ui<
        TimeManagementUi.Reaction,
        TimeManagementUi.UiState,
        FragmentTimemanagementBinding
        >() {
    sealed interface Reaction {
        object CloseClicked : Reaction
        class ChapterClicked(val chapter: Chapter) : Reaction
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
        adapter = AsyncListDifferDelegationAdapter(
            ItemCallback(),
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
                                ChapterState.Current -> R.drawable.ic_dot
                                ChapterState.Completed -> R.drawable.ic_tick_in_circle
                                ChapterState.Locked -> R.drawable.ic_lock
                            },
                            0,
                            when (item.state) {
                                ItemState.Collapsed -> R.drawable.ic_tick_down
                                ItemState.Expanded -> R.drawable.ic_tick_up
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
                                ChapterState.Current -> R.drawable.ic_dot
                                ChapterState.Completed -> R.drawable.ic_tick_in_circle
                                ChapterState.Locked -> R.drawable.ic_lock
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

    class ItemCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            (oldItem is Item.ChapterItem && newItem is Item.ChapterItem) ||
                    (oldItem is Item.SubchapterItem && newItem is Item.SubchapterItem)

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = when {
            oldItem is Item.ChapterItem && newItem is Item.ChapterItem -> {
                oldItem.state == newItem.state
            }
            oldItem is Item.SubchapterItem && newItem is Item.SubchapterItem -> true
            else -> false
        }
    }

    enum class ItemState { Collapsed, Expanded }

    sealed interface Item {
        class ChapterItem(
            val chapter: Chapter,
            val state: ItemState,
            val isCollapsed: Boolean
        ) : Item

        class SubchapterItem(val chapter: Chapter, val state: ItemState) : Item
    }
}