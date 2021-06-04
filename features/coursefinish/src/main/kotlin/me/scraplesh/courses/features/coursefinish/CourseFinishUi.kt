package me.scraplesh.courses.features.coursefinish

import me.scraplesh.courses.features.coursefinish.databinding.FragmentCourseFinishBinding
import me.scraplesh.courses.mvi.Ui
import javax.inject.Inject

class CourseFinishUi @Inject constructor() :
    Ui<CourseFinishUi.Reaction, CourseFinishUi.UiState, FragmentCourseFinishBinding>() {

    class Reaction
    class UiState

    override fun bindViews(view: FragmentCourseFinishBinding) {
    }
}
