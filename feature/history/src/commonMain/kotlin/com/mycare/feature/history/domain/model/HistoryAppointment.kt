package com.mycare.feature.history.domain.model

import com.mycare.core.common.domain.model.AppointmentStatus
import kotlinx.datetime.LocalDateTime

internal data class HistoryAppointment(
    val id: String,
    val name: String,
    val date: LocalDateTime,
    val locationName: String,
    val status: AppointmentStatus,
)
