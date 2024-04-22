package com.mycare.feature.appointments.common.presentation

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.bumble.appyx.navigation.collections.ImmutableList
import com.bumble.appyx.navigation.collections.toImmutableList
import com.mycare.feature.appointments.common.domain.model.Address
import com.mycare.feature.appointments.common.domain.model.Appointment
import com.mycare.feature.appointments.common.domain.model.AppointmentStatus
import com.mycare.feature.appointments.common.domain.model.Location
import com.mycare.feature.appointments.common.presentation.model.AddressUiModel
import com.mycare.feature.appointments.common.presentation.model.AppointmentStatusUiModel
import com.mycare.feature.appointments.common.presentation.model.AppointmentUiModel
import com.mycare.feature.appointments.common.presentation.model.LocationUiModel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

internal fun List<Appointment>.toUiModels(): Pair<AppointmentUiModel, ImmutableList<AppointmentUiModel>> =
    let {
        val dateSortedAppointments = sortedBy { it.date }
        val upcomingAppointment = dateSortedAppointments.first()
        val futureAppointments = dateSortedAppointments
            .minus(upcomingAppointment)
            .map { it.toUiModel() }
            .toImmutableList()
        Pair(
            first = upcomingAppointment.toUiModel(),
            second = futureAppointments,
        )
    }

internal fun Appointment.toUiModel() = AppointmentUiModel(
    id = id,
    name = name,
    day = date.dayOfMonth.toString(),
    month = date.toReadableMonth(),
    time = date.time.toReadableTime(),
    handledBy = handledBy,
    estimatedDuration = estimatedDurationMinutes,
    location = location.toUiModel(),
    conclusion = conclusion,
    status = status.toUiModel(),
)

private fun Location.toUiModel() = LocationUiModel(
    name = name,
    address = address.toUiModel(),
    phone = phone,
    imageUrl = imageUrl,
)

private fun Address.toUiModel() = AddressUiModel(
    name = name,
    additionalDirections = additionalDirections,
)

private fun AppointmentStatus.toUiModel() = when (this) {
    AppointmentStatus.SCHEDULED -> AppointmentStatusUiModel.SCHEDULED
    AppointmentStatus.COMPLETED -> AppointmentStatusUiModel.COMPLETED
    AppointmentStatus.CANCELED -> AppointmentStatusUiModel.CANCELED
}

private fun LocalTime.toReadableTime() = "$hour:$minute"

private fun LocalDateTime.toReadableMonth() = month
    .name
    .lowercase()
    .capitalize(Locale.current)
