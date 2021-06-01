package me.scraplesh.courses.features.timemanagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Effect
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Event
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Intention
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel

class TimeManagementViewModel(feature: TimeManagementFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature) {

    class Factory @AssistedInject constructor(
        @Assisted private val course: Course,
        private val featureFactory: TimeManagementFeature.AssistedFactory
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(TimeManagementFeature::class.java)
                .newInstance(featureFactory.create(course))
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(course: Course): Factory
    }
}