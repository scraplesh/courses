package me.scraplesh.courses.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import me.scraplesh.courses.R
import me.scraplesh.courses.navigation.CoursesNavEvent.OnboardingNavEvent
import me.scraplesh.courses.navigation.CoursesNavEvent.SignInNavEvent
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
            OnboardingNavEvent.ShowSignUp -> {
                navController.navigate(R.id.action_onboardingFragment_to_signUpFragment)
            }
            SignInNavEvent.NavigatedBack -> navController.navigateUp()
            SignInNavEvent.SignedIn -> navController.navigate(R.id.action_signInFragment_to_main)
            SignInNavEvent.ShowPasswordRecovery -> {
                navController.navigate(R.id.action_signInFragment_to_browserActivity)
            }
        }
    }
}