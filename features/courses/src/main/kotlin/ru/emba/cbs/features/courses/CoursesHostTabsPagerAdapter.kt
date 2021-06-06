package ru.emba.cbs.features.courses

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.emba.cbs.features.courses.about.AboutFragment
import ru.emba.cbs.features.courses.list.CoursesFragment
import ru.emba.cbs.features.courses.reviews.ReviewsFragment

class CoursesHostTabsPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = COUNT_ITEMS

    override fun createFragment(position: Int): Fragment =
        when (CoursesHostTabItem.values()[position]) {
            CoursesHostTabItem.Courses -> CoursesFragment()
            CoursesHostTabItem.About -> AboutFragment()
            CoursesHostTabItem.Reviews -> ReviewsFragment()
        }

    private companion object {
        const val COUNT_ITEMS = 3
    }
}
