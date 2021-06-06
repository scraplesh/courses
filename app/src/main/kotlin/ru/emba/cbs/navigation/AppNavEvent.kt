package ru.emba.cbs.navigation

sealed interface AppNavEvent : NavEvent {
    enum class OnboardingNavEvent : CoursesNavEvent { ShowSignIn, ShowSignUp }
    enum class SignInNavEvent : CoursesNavEvent { NavigatedBack, SignedIn, ShowPasswordRecovery }
    enum class SignUpNavEvent : CoursesNavEvent { NavigatedBack, SignedUp, PrivacyPolicyRequested }
    enum class CoursesHostNavEvent : CoursesNavEvent { NavigatedBack, ShowSettings }

    sealed interface CourseNavEvent : CoursesNavEvent {
        object NavigatedBack : CoursesNavEvent
        class ShowTimeManagement(val course: ru.emba.cbs.domain.model.Course) : CoursesNavEvent
    }

    enum class TimeManagementNavEvent : CoursesNavEvent { Close }
    enum class SettingsNavEvent : CoursesNavEvent { NavigateBack, SignedOut }

    sealed interface CoursesNavEvent : AppNavEvent {
        class ShowCourse(val course: ru.emba.cbs.domain.model.Course) : CoursesNavEvent
    }
}