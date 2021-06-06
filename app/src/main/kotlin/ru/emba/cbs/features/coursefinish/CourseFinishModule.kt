package ru.emba.cbs.features.coursefinish

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import ru.emba.cbs.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class CourseFinishModule {
    @Binds
    @FragmentScoped
    abstract fun bindSignInBindings(
        bindings: CourseFinishMviBindings
    ): MviBindings<CourseFinishUi, CourseFinishViewModel>
}