package me.scraplesh.courses.features.courses

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.courses.databinding.FragmentCoursesHostBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.view.clicks

class CoursesHostUi @AssistedInject constructor(
    @Assisted fragmentManager: FragmentManager,
    @Assisted lifecycle: Lifecycle
) : Ui<CoursesHostUi.Reaction, Unit, FragmentCoursesHostBinding>() {

    enum class Reaction { SettingsClicked, BackClicked }

    private lateinit var tabLayout: TabLayout
    private var settingsButton: View by didSet {
        clicks().react { Reaction.SettingsClicked }
    }
    private var viewPager: ViewPager2 by didSet {
        adapter = CoursesHostTabsPagerAdapter(fragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, this, true) { tab, position ->
            tab.text = context.getString(
                when (CoursesHostTabItem.values()[position]) {
                    CoursesHostTabItem.Courses -> R.string.courses_courses
                    CoursesHostTabItem.About -> R.string.courses_about
                    CoursesHostTabItem.Reviews -> R.string.courses_reviews
                }
            )
        }
            .apply { attach() }
    }

    fun onBackPress(): Boolean {
        react(Reaction.BackClicked)
        return true
    }

    override fun bindViews(view: FragmentCoursesHostBinding) {
        with(view) {
            settingsButton = imagebuttonCourseshostSettings
            tabLayout = tablayoutCourseshost
            viewPager = viewpagerCourseshost
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(
            fragmentManager: FragmentManager,
            lifecycle: Lifecycle
        ): CoursesHostUi
    }
}
