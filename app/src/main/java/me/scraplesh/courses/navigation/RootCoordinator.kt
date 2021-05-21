package me.scraplesh.courses.navigation

import androidx.navigation.NavController
import me.scraplesh.courses.R
import me.scraplesh.courses.navigation.CoursesNavEvent.OnboardingNavEvent
import javax.inject.Inject

class RootCoordinator @Inject constructor(
    private val navController: NavController
) : Coordinator {
    override suspend fun emit(value: NavEvent) {
        when (value) {
            is CoursesNavEvent -> onNavEvent(value)
        }
    }

    private fun onNavEvent(event: CoursesNavEvent) {
        when (event) {
            OnboardingNavEvent.ShowSignIn -> {
                navController.navigate(R.id.action_onboardingFragment_to_signInFragment)
            }
            OnboardingNavEvent.ShowSignUp -> TODO()
        }
    }
}