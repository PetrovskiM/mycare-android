package com.mycare.feature.history.presentation.model

import com.mycare.core.ui.presentation.model.AppointmentStatusUiModel

internal data class HistoryUiModel(
    val id: String,
    val name: String,
    val locationName: String,
    val day: String,
    val month: String,
    val time: String,
    val status: AppointmentStatusUiModel,
)
