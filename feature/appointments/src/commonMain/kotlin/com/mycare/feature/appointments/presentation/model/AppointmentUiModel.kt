package com.mycare.feature.appointments.presentation.model

internal data class AppointmentUiModel(
    val id: String,
    val name: String,
    val day: String,
    val month: String,
    val time: String,
)

internal val fakeAppointment = AppointmentUiModel(
    id = "1",
    name = "Appointment name",
    day = "10",
    month = "October",
    time = "13:45",
)