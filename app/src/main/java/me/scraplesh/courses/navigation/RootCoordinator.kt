package me.scraplesh.courses.navigation

import androidx.navigation.NavController
import me.scraplesh.courses.R
import me.scraplesh.courses.common.model.CourseDto
import me.scraplesh.courses.features.course.CourseFragmentDirections
import me.scraplesh.courses.features.courses.CoursesHostFragmentDirections
import me.scraplesh.courses.features.courses.list.CoursesFragmentDirections
import me.scraplesh.courses.features.onboarding.OnboardingFragmentDirections
import me.scraplesh.courses.features.signin.SignInFragmentDirections
import me.scraplesh.courses.features.signup.SignUpFragmentDirections
import me.scraplesh.courses.navigation.AppNavEvent.CourseNavEvent
import me.scraplesh.courses.navigation.AppNavEvent.CoursesHostNavEvent
import me.scraplesh.courses.navigation.AppNavEvent.CoursesNavEvent
import me.scraplesh.courses.navigation.AppNavEvent.OnboardingNavEvent
import me.scraplesh.courses.navigation.AppNavEvent.SettingsNavEvent
import me.scraplesh.courses.navigation.AppNavEvent.SignInNavEvent
import me.scraplesh.courses.navigation.AppNavEvent.SignUpNavEvent
import me.scraplesh.courses.navigation.AppNavEvent.TimeManagementNavEvent
import javax.inject.Inject

class RootCoordinator @Inject constructor(
    private val navController: NavController
) : Coordinator {
    override suspend fun emit(value: NavEvent) {
        when (value) {
            is AppNavEvent -> onNavEvent(value)
        }
    }

    private fun onNavEvent(event: AppNavEvent) {
        when (event) {
            OnboardingNavEvent.ShowSignIn -> {
                navController.navigate(
                    OnboardingFragmentDirections.actionOnboardingFragmentToSignInFragment()
                )
            }
            OnboardingNavEvent.ShowSignUp -> {
                navController.navigate(
                    OnboardingFragmentDirections.actionOnboardingFragmentToSignUpFragment()
                )
            }
            SignInNavEvent.NavigatedBack -> navController.navigateUp()
            SignInNavEvent.SignedIn -> {
                navController.navigate(SignInFragmentDirections.actionSignInFragmentToMain())
            }
            SignInNavEvent.ShowPasswordRecovery -> {
                navController.navigate(
                    SignInFragmentDirections.actionSignInFragmentToBrowserActivity()
                )
            }
            SignUpNavEvent.NavigatedBack -> navController.navigateUp()
            SignUpNavEvent.SignedUp -> {
                navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToMain())
            }
            CoursesHostNavEvent.NavigatedBack -> navController.navigateUp()
            CoursesHostNavEvent.ShowSettings -> {
                navController.navigate(
                    CoursesHostFragmentDirections.actionCoursesHostFragmentToSettingsFragment()
                )
            }
            CourseNavEvent.NavigatedBack -> navController.navigateUp()
            is CourseNavEvent.ShowTimeManagement -> {
                navController.navigate(
                    CourseFragmentDirections.actionCourseFragmentToTimeManagementFragment(
                        CourseDto(event.course)
                    )
                )
            }
            TimeManagementNavEvent.Close -> navController.navigateUp()
            SettingsNavEvent.NavigateBack -> navController.navigateUp()
            SettingsNavEvent.SignedOut -> navController.navigate(R.id.start)
            is CoursesNavEvent.ShowCourse -> {
                CoursesFragmentDirections.actionCoursesFragmentToCourseFragment(
                    CourseDto(event.course)
                )
            }
        }
    }
}