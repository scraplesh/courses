package me.scraplesh.courses.features.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.features.course.CourseFeature.Effect
import me.scraplesh.courses.features.course.CourseFeature.Event
import me.scraplesh.courses.features.course.CourseFeature.Intention
import me.scraplesh.courses.features.course.CourseFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel

class CourseViewModel(feature: CourseFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature) {

    class Factory @AssistedInject constructor(
        @Assisted private val course: Course,
        private val courseFeatureFactory: CourseFeature.AssistedFactory
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(CourseFeature::class.java)
                .newInstance(courseFeatureFactory.create(course))
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(course: Course): Factory
    }
}
