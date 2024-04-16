package com.mycare.feature.appointments.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("date") val date: Instant,
)