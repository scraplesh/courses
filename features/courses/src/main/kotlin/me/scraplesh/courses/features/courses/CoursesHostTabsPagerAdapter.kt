package me.scraplesh.courses.features.courses

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CoursesHostTabsPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val coursesFragmentProvider: () -> Fragment,
    private val aboutFragmentProvider: () -> Fragment,
    private val reviewsFragment: () -> Fragment
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    var titles = emptyList<CoursesHostTabItem>()

    override fun getItemCount(): Int = titles.size

    override fun createFragment(position: Int): Fragment = when (titles[position]) {
        is CoursesHostTabItem.Courses -> coursesFragmentProvider()
        is CoursesHostTabItem.Reviews -> aboutFragmentProvider()
        is CoursesHostTabItem.About -> reviewsFragment()
    }
}
