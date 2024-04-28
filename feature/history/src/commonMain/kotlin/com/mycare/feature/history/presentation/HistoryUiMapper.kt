package com.mycare.feature.history.presentation

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.mycare.core.common.domain.model.AppointmentStatus
import com.mycare.core.ui.presentation.model.AppointmentStatusUiModel
import com.mycare.core.ui.util.toImmutableList
import com.mycare.feature.history.domain.model.HistoryAppointment
import com.mycare.feature.history.presentation.model.HistoryUiModel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

internal fun List<HistoryAppointment>.toUiModels() = sortedBy { it.date }
    .map { it.toUiModel() }
    .toImmutableList()

private fun HistoryAppointment.toUiModel() = HistoryUiModel(
    id = id,
    name = name,
    locationName = locationName,
    day = date.dayOfMonth.toString(),
    month = date.toReadableMonth(),
    time = date.time.toReadableTime(),
    status = status.toUiModel(),
)

private fun LocalDateTime.toReadableMonth() = month
    .name
    .lowercase()
    .capitalize(Locale.current)

private fun LocalTime.toReadableTime() = "$hour:$minute"

private fun AppointmentStatus.toUiModel() = when (this) {
    AppointmentStatus.SCHEDULED -> AppointmentStatusUiModel.SCHEDULED
    AppointmentStatus.COMPLETED -> AppointmentStatusUiModel.COMPLETED
    AppointmentStatus.CANCELED -> AppointmentStatusUiModel.CANCELED
}
