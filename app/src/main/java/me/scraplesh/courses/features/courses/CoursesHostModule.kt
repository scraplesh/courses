package me.scraplesh.courses.features.courses

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.scraplesh.courses.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class CoursesHostModule {
    @Binds
    @FragmentScoped
    abstract fun bindSignInBindings(
        bindings: CoursesHostMviBindings
    ): MviBindings<CoursesHostUi, Unit>
}