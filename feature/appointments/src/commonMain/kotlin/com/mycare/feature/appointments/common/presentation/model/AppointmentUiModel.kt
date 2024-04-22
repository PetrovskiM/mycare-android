package com.mycare.feature.appointments.common.presentation.model

internal data class AppointmentUiModel(
    val id: String,
    val name: String,
    val day: String,
    val month: String,
    val time: String,
    val handledBy: String,
    val estimatedDuration: Int?,
    val location: LocationUiModel,
    val conclusion: String?,
    val status: AppointmentStatusUiModel,
)
