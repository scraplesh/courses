package ru.emba.cbs.navigation

import androidx.navigation.NavController
import ru.emba.cbs.R
import ru.emba.cbs.common.model.CourseDto
import ru.emba.cbs.features.course.CourseFragmentDirections
import ru.emba.cbs.features.courses.CoursesHostFragmentDirections
import ru.emba.cbs.features.courses.list.CoursesFragmentDirections
import ru.emba.cbs.features.onboarding.OnboardingFragmentDirections
import ru.emba.cbs.features.signin.SignInFragmentDirections
import ru.emba.cbs.features.signup.SignUpFragmentDirections
import ru.emba.cbs.navigation.AppNavEvent.CourseNavEvent
import ru.emba.cbs.navigation.AppNavEvent.CoursesHostNavEvent
import ru.emba.cbs.navigation.AppNavEvent.CoursesNavEvent
import ru.emba.cbs.navigation.AppNavEvent.OnboardingNavEvent
import ru.emba.cbs.navigation.AppNavEvent.SettingsNavEvent
import ru.emba.cbs.navigation.AppNavEvent.SignInNavEvent
import ru.emba.cbs.navigation.AppNavEvent.SignUpNavEvent
import ru.emba.cbs.navigation.AppNavEvent.TimeManagementNavEvent
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
            SignUpNavEvent.PrivacyPolicyRequested -> navController.navigate(
                SignUpFragmentDirections.actionSignUpFragmentToPrivacyPolicyActivity()
            )
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