package com.mycare.feature.appointments.presentation

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.bumble.appyx.navigation.collections.ImmutableList
import com.bumble.appyx.navigation.collections.toImmutableList
import com.mycare.feature.appointments.domain.model.Appointment
import com.mycare.feature.appointments.presentation.model.AppointmentUiModel
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
)

private fun LocalTime.toReadableTime() = "$hour:$minute"

private fun LocalDateTime.toReadableMonth() = month
    .name
    .lowercase()
    .capitalize(Locale.current)
