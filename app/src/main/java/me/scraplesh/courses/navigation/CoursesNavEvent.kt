package me.scraplesh.courses.navigation

import me.scraplesh.courses.domain.model.Course

sealed interface CoursesNavEvent : NavEvent {
    enum class OnboardingNavEvent : CoursesNavEvent { ShowSignIn, ShowSignUp }
    enum class SignInNavEvent : CoursesNavEvent { NavigatedBack, SignedIn, ShowPasswordRecovery }
    enum class SignUpNavEvent : CoursesNavEvent { NavigatedBack, SignedUp }
    enum class CoursesHostNavEvent : CoursesNavEvent { NavigatedBack, ShowSettings }
    sealed interface CourseNavEvent : CoursesNavEvent {
        object NavigatedBack : CoursesNavEvent
        class ShowTimeManagement(val course: Course) : CoursesNavEvent
    }
    enum class TimeManagementNavEvent : CoursesNavEvent { Close }
    enum class SettingsNavEvent : CoursesNavEvent { NavigateBack, SignedOut }
}