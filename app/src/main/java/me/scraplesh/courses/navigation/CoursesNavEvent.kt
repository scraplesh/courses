package me.scraplesh.courses.navigation

sealed interface CoursesNavEvent : NavEvent {
    enum class OnboardingNavEvent : CoursesNavEvent { ShowSignIn, ShowSignUp }
}