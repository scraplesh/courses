package me.scraplesh.courses.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import me.scraplesh.courses.R
import me.scraplesh.courses.navigation.Coordinator
import me.scraplesh.courses.navigation.RootCoordinator

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {
    @Provides
    fun provideNavController(@ActivityContext context: Context): NavController {
        val navHostFragment = (context as AppCompatActivity).supportFragmentManager
            .findFragmentById(R.id.fragmentcontainer_main) as NavHostFragment
        return navHostFragment.navController
    }

    @Provides
    fun provideCoordinator(coordinator: RootCoordinator): Coordinator = coordinator
}