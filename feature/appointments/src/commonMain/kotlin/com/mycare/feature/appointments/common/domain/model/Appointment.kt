package com.mycare.feature.appointments.common.domain.model

import kotlinx.datetime.LocalDateTime

internal data class Appointment(
    val id: String,
    val name: String,
    val date: LocalDateTime,
    val handledBy: String,
    val estimatedDurationMinutes: Int?,
    val location: Location,
    val conclusion: String?,
    val status: AppointmentStatus,
)
