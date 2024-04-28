package com.mycare.core.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    @SerialName("name") val name: String,
    @SerialName("additionalDirections") val additionalDirections: String?,
)
