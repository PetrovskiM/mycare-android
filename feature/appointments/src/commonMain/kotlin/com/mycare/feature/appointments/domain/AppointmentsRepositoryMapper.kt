package com.mycare.feature.appointments.domain

import com.mycare.feature.appointments.data.model.AppointmentResponse
import com.mycare.feature.appointments.domain.model.Appointment
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun List<AppointmentResponse>.toDomainModels(): List<Appointment> =
    map { it.toDomainModel() }

internal fun AppointmentResponse.toDomainModel() = Appointment(
    id = id,
    name = name,
    date = date.toLocalDateTime(timeZone = timeZone)
)

private val timeZone = TimeZone.currentSystemDefault()