package me.scraplesh.courses.navigation

sealed interface CoursesNavEvent : NavEvent {
    enum class OnboardingNavEvent : CoursesNavEvent { ShowSignIn, ShowSignUp }
    enum class SignInNavEvent : CoursesNavEvent { NavigatedBack, SignedIn, ShowPasswordRecovery }
    enum class SignUpNavEvent : CoursesNavEvent { NavigatedBack, SignedUp }
    enum class CoursesHostNavEvent : CoursesNavEvent { NavigatedBack, ShowSettings }
}