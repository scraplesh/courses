package ru.emba.cbs.features.onboarding

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import ru.emba.cbs.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class OnboardingModule {
    @Binds
    @FragmentScoped
    abstract fun bindOnboardingBindings(
        bindings: OnboardingMviBindings
    ): MviBindings<OnboardingUi, OnboardingViewModel>
}