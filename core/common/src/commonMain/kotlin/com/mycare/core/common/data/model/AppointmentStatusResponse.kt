package com.mycare.core.common.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class AppointmentStatusResponse {
    SCHEDULED,
    COMPLETED,
    CANCELED,
}
