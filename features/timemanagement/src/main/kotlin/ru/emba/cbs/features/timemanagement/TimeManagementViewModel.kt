package ru.emba.cbs.features.timemanagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Effect
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Event
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Intention
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel

class TimeManagementViewModel(feature: TimeManagementFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature) {

    class Factory @AssistedInject constructor(
        @Assisted private val course: ru.emba.cbs.domain.model.Course,
        private val featureFactory: TimeManagementFeature.AssistedFactory
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(TimeManagementFeature::class.java)
                .newInstance(featureFactory.create(course))
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(course: ru.emba.cbs.domain.model.Course): Factory
    }
}