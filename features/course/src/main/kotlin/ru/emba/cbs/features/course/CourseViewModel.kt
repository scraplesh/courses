package ru.emba.cbs.features.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.emba.cbs.features.course.CourseFeature.Effect
import ru.emba.cbs.features.course.CourseFeature.Event
import ru.emba.cbs.features.course.CourseFeature.Intention
import ru.emba.cbs.features.course.CourseFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel

class CourseViewModel(feature: CourseFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature) {

    class Factory @AssistedInject constructor(
        @Assisted private val course: ru.emba.cbs.domain.model.Course,
        private val courseFeatureFactory: CourseFeature.AssistedFactory
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(CourseFeature::class.java)
                .newInstance(courseFeatureFactory.create(course))
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(course: ru.emba.cbs.domain.model.Course): Factory
    }
}
