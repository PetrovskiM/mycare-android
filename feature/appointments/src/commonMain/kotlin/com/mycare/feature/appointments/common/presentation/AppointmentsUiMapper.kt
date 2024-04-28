package com.mycare.feature.appointments.common.presentation

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.mycare.core.common.domain.model.AppointmentStatus
import com.mycare.core.ui.presentation.model.AddressUiModel
import com.mycare.core.ui.presentation.model.AppointmentRatingUiModel
import com.mycare.core.ui.presentation.model.AppointmentStatusUiModel
import com.mycare.core.ui.presentation.model.AppointmentUiModel
import com.mycare.core.ui.presentation.model.LocationUiModel
import com.mycare.core.ui.util.ImmutableList
import com.mycare.core.ui.util.emptyImmutableList
import com.mycare.core.ui.util.toImmutableList
import com.mycare.feature.appointments.common.domain.model.Address
import com.mycare.feature.appointments.common.domain.model.Appointment
import com.mycare.feature.appointments.common.domain.model.Location
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
    rating = AppointmentRatingUiModel(emptyImmutableList()),
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
