package com.mycare.feature.appointments.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AddressResponse(
    @SerialName("name") val name: String,
    @SerialName("additionalDirections") val additionalDirections: String?,
)
