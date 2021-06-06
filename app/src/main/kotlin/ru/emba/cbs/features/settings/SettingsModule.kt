package ru.emba.cbs.features.settings

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import ru.emba.cbs.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class SettingsModule {
    @Binds
    @FragmentScoped
    abstract fun bindSignInBindings(
        bindings: SettingsMviBindings
    ): MviBindings<SettingsUi, SettingsViewModel>
}