package com.mycare.feature.appointments.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LocationResponse(
    @SerialName("name") val name: String,
    @SerialName("address") val address: AddressResponse,
    @SerialName("phone") val phone: String?,
    @SerialName("imageUrl") val imageUrl: String?,
)
