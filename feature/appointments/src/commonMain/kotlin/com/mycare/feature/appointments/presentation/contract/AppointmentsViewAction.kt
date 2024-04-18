package com.mycare.feature.appointments.presentation.contract

internal sealed interface AppointmentsViewAction {
    data object Retry : AppointmentsViewAction
}
