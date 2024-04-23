package com.mycare.core.ui.presentation.model

import mycare.core.ui.generated.resources.Res
import mycare.core.ui.generated.resources.appointments_canceled
import mycare.core.ui.generated.resources.appointments_completed
import mycare.core.ui.generated.resources.appointments_scheduled
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
enum class AppointmentStatusUiModel(val text: StringResource) {
    SCHEDULED(Res.string.appointments_scheduled),
    COMPLETED(Res.string.appointments_completed),
    CANCELED(Res.string.appointments_canceled),
}
