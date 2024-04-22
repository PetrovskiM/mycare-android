package com.mycare.feature.appointments.common.presentation.model

internal data class LocationUiModel(
    val name: String,
    val address: AddressUiModel,
    val phone: String?,
    val imageUrl: String?,
)
