package com.mycare.feature.appointments.common.domain.model

internal data class Location(
    val name: String,
    val address: Address,
    val phone: String?,
    val imageUrl: String?,
)
