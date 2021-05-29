package me.scraplesh.courses.features.course

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.scraplesh.courses.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class CourseModule {
    @Binds
    @FragmentScoped
    abstract fun bindSignInBindings(
        bindings: CourseMviBindings
    ): MviBindings<CourseUi, CourseViewModel>
}