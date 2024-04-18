package com.mycare.feature.appointments.details.presentation.contract

internal sealed interface AppointmentDetailsViewAction {
    data object Retry : AppointmentDetailsViewAction
}
