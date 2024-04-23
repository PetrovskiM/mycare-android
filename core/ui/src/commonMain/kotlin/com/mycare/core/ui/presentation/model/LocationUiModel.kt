package com.mycare.core.ui.presentation.model

data class LocationUiModel(
    val name: String,
    val address: AddressUiModel,
    val phone: String?,
    val imageUrl: String?,
)
