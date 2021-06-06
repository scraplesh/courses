package me.scraplesh.courses.features.signin

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.scraplesh.courses.mvi.MviBindings

@Module
@InstallIn(FragmentComponent::class)
abstract class SignInModule {
    @Binds
    @FragmentScoped
    abstract fun bindSignInBindings(
        bindings: SignInMviBindings
    ): MviBindings<SignInUi, SignInViewModel>
}