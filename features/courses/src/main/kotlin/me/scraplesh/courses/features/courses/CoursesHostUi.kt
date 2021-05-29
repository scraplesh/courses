package me.scraplesh.courses.features.courses

import android.view.View
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.courses.databinding.FragmentCoursesHostBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.view.clicks
import javax.inject.Inject

class CoursesHostUi @Inject constructor() :
    Ui<CoursesHostUi.Reaction, Unit, FragmentCoursesHostBinding>() {

    enum class Reaction { SettingsClicked, BackClicked }

    private var settingsButton: View by didSet {
        clicks().react { Reaction.SettingsClicked }
    }

    fun onBackPress(): Boolean {
        react(Reaction.BackClicked)
        return true
    }

    override fun bindViews(view: FragmentCoursesHostBinding) {
        with(view) {
            settingsButton = imagebuttonCourseshostSettings
        }
    }
}
