package com.mycare.feature.appointments.common.domain

import com.mycare.feature.appointments.common.data.model.AddressResponse
import com.mycare.feature.appointments.common.data.model.AppointmentResponse
import com.mycare.feature.appointments.common.data.model.AppointmentStatusResponse
import com.mycare.feature.appointments.common.data.model.LocationResponse
import com.mycare.feature.appointments.common.domain.model.Address
import com.mycare.feature.appointments.common.domain.model.Appointment
import com.mycare.feature.appointments.common.domain.model.AppointmentStatus
import com.mycare.feature.appointments.common.domain.model.Location
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun List<AppointmentResponse>.toDomainModels(): List<Appointment> =
    map { it.toDomainModel() }

internal fun AppointmentResponse.toDomainModel() = Appointment(
    id = id,
    name = name,
    date = date.toLocalDateTime(timeZone = timeZone),
    handledBy = handledBy,
    estimatedDurationMinutes = estimatedDurationMinutes,
    location = location.toDomainModel(),
    conclusion = conclusion,
    status = status.toDomainModel(),
)

private fun LocationResponse.toDomainModel() = Location(
    name = name,
    address = address.toDomainModel(),
    phone = phone,
    imageUrl = imageUrl,
)

private fun AddressResponse.toDomainModel() = Address(
    name = name,
    additionalDirections = additionalDirections,
)

private fun AppointmentStatusResponse.toDomainModel() = when (this) {
    AppointmentStatusResponse.SCHEDULED -> AppointmentStatus.SCHEDULED
    AppointmentStatusResponse.COMPLETED -> AppointmentStatus.COMPLETED
    AppointmentStatusResponse.CANCELED -> AppointmentStatus.CANCELED
}

private val timeZone = TimeZone.currentSystemDefault()
