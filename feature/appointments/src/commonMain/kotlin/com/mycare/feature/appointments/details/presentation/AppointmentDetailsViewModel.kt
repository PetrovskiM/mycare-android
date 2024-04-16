package com.mycare.feature.appointments.details.presentation

import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class AppointmentDetailsViewModel internal constructor(
    @InjectedParam val appointmentId: String,
) {

}