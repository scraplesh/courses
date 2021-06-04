package me.scraplesh.courses.features.coursefinish

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.scraplesh.courses.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class CourseFinishModule {
    @Binds
    @FragmentScoped
    abstract fun bindSignInBindings(
        bindings: CourseFinishMviBindings
    ): MviBindings<CourseFinishUi, CourseFinishViewModel>
}