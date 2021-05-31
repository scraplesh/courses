package me.scraplesh.courses.features.courses

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.scraplesh.courses.features.courses.list.CoursesUi
import me.scraplesh.courses.features.courses.list.CoursesViewModel
import me.scraplesh.courses.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class CoursesModule {
    @Binds
    @FragmentScoped
    abstract fun bindCoursesHostBindings(
        bindings: CoursesHostMviBindings
    ): MviBindings<CoursesHostUi, Unit>

    @Binds
    @FragmentScoped
    abstract fun bindCoursesBindings(
        bindings: CoursesMviBindings
    ): MviBindings<CoursesUi, CoursesViewModel>
}