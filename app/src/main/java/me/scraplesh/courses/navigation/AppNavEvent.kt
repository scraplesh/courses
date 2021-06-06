package me.scraplesh.courses.navigation

import me.scraplesh.courses.domain.model.Course

sealed interface AppNavEvent : NavEvent {
    enum class OnboardingNavEvent : CoursesNavEvent { ShowSignIn, ShowSignUp }
    enum class SignInNavEvent : CoursesNavEvent { NavigatedBack, SignedIn, ShowPasswordRecovery }
    enum class SignUpNavEvent : CoursesNavEvent { NavigatedBack, SignedUp, PrivacyPolicyRequested }
    enum class CoursesHostNavEvent : CoursesNavEvent { NavigatedBack, ShowSettings }

    sealed interface CourseNavEvent : CoursesNavEvent {
        object NavigatedBack : CoursesNavEvent
        class ShowTimeManagement(val course: Course) : CoursesNavEvent
    }

    enum class TimeManagementNavEvent : CoursesNavEvent { Close }
    enum class SettingsNavEvent : CoursesNavEvent { NavigateBack, SignedOut }

    sealed interface CoursesNavEvent : AppNavEvent {
        class ShowCourse(val course: Course) : CoursesNavEvent
    }
}