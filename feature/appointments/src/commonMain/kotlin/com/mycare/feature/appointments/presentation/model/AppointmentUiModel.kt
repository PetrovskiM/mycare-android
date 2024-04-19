package com.mycare.feature.appointments.presentation.model

import com.mycare.feature.appointments.common.model.AppointmentStatusUiModel

internal data class AppointmentUiModel(
    val id: String,
    val name: String,
    val day: String,
    val month: String,
    val time: String,
    val handledBy: String,
    val statusUiModel: AppointmentStatusUiModel,
    val location: LocationUiModel,
)
