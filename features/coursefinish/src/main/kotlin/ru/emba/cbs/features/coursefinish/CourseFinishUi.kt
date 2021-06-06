package ru.emba.cbs.features.coursefinish

import ru.emba.cbs.features.coursefinish.databinding.FragmentCourseFinishBinding
import ru.emba.cbs.mvi.Ui
import javax.inject.Inject

class CourseFinishUi @Inject constructor() :
    Ui<CourseFinishUi.Reaction, CourseFinishUi.UiState, FragmentCourseFinishBinding>() {

    class Reaction
    class UiState

    override fun bindViews(view: FragmentCourseFinishBinding) {
    }
}
