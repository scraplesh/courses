package me.scraplesh.courses.features.timemanagement

import me.scraplesh.courses.features.timemanagement.TimeManagementViewModel.Event
import me.scraplesh.courses.features.timemanagement.TimeManagementViewModel.Intention
import me.scraplesh.courses.mvi.StatelessViewModel

class TimeManagementViewModel : StatelessViewModel<Intention, Event>(
    eventEmitter = { intention, _, _ ->
        when (intention) {
            Intention.ShowRegistration -> Event.RegisterRequested
            Intention.ShowLogin -> Event.LoginRequested
        }
    }
) {
    enum class Intention { ShowRegistration, ShowLogin }
    enum class Event { RegisterRequested, LoginRequested }
}
