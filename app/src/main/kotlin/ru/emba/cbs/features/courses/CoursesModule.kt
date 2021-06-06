package ru.emba.cbs.features.courses

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import ru.emba.cbs.features.courses.list.CoursesUi
import ru.emba.cbs.features.courses.list.CoursesViewModel
import ru.emba.cbs.mvi.MviBindings

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