package me.scraplesh.courses.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.scraplesh.courses.features.onboarding.OnboardingMviBindings
import me.scraplesh.courses.features.onboarding.OnboardingUi
import me.scraplesh.courses.features.onboarding.OnboardingViewModel
import me.scraplesh.courses.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class OnboardingModule {
    @Binds
    @FragmentScoped
    abstract fun bindOnboardingBindings(
        bindings: OnboardingMviBindings
    ): MviBindings<OnboardingUi, OnboardingViewModel>
}