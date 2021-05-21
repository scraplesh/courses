package me.scraplesh.courses.features.courses

sealed class CoursesHostTabItem(val title: String) {
    class Courses(title: String) : CoursesHostTabItem(title)
    class About(title: String) : CoursesHostTabItem(title)
    class Reviews(title: String) : CoursesHostTabItem(title)
}