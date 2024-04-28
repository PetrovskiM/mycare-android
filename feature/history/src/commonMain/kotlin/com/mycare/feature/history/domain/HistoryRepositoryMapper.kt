package com.mycare.feature.history.domain

import com.mycare.core.common.data.model.AppointmentResponse
import com.mycare.core.common.data.model.AppointmentStatusResponse
import com.mycare.core.common.domain.model.AppointmentStatus
import com.mycare.feature.history.domain.model.HistoryAppointment
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun List<AppointmentResponse>.toDomainModels(): List<HistoryAppointment> =
    map { it.toDomainModel() }

internal fun AppointmentResponse.toDomainModel() = HistoryAppointment(
    id = id,
    name = name,
    date = date.toLocalDateTime(timeZone = timeZone),
    locationName = location.name,
    status = status.toDomainModel(),
)

private fun AppointmentStatusResponse.toDomainModel() = when (this) {
    AppointmentStatusResponse.SCHEDULED -> AppointmentStatus.SCHEDULED
    AppointmentStatusResponse.COMPLETED -> AppointmentStatus.COMPLETED
    AppointmentStatusResponse.CANCELED -> AppointmentStatus.CANCELED
}

private val timeZone = TimeZone.currentSystemDefault()
