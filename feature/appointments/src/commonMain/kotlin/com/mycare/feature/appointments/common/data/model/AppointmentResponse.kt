package com.mycare.feature.appointments.common.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AppointmentResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("date") val date: Instant,
    @SerialName("handledBy") val handledBy: String,
    @SerialName("estimatedDurationMinutes") val estimatedDurationMinutes: Int?,
    @SerialName("location") val location: LocationResponse,
    @SerialName("conclusion") val conclusion: String?,
    @SerialName("status") val status: AppointmentStatusResponse,
)
