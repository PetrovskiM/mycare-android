package com.mycare.feature.appointments.domain.model

import kotlinx.datetime.LocalDateTime

internal data class Appointment(
    val id: String,
    val name: String,
    val date: LocalDateTime,
)
