package com.mycare.feature.appointments.common.model

import mycare.feature.appointments.generated.resources.Res
import mycare.feature.appointments.generated.resources.appointments_canceled
import mycare.feature.appointments.generated.resources.appointments_completed
import mycare.feature.appointments.generated.resources.appointments_scheduled
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
internal enum class AppointmentStatusUiModel(val text: StringResource) {
    SCHEDULED(Res.string.appointments_scheduled),
    COMPLETED(Res.string.appointments_completed),
    CANCELED(Res.string.appointments_canceled),
}
