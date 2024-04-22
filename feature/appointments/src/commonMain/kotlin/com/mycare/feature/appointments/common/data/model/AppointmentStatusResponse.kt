package com.mycare.feature.appointments.common.data.model

import kotlinx.serialization.Serializable

@Serializable
internal enum class AppointmentStatusResponse {
    SCHEDULED,
    COMPLETED,
    CANCELED,
}
