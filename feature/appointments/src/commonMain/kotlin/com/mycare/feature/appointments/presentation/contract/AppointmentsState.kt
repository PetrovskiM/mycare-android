package com.mycare.feature.appointments.presentation.contract

import com.bumble.appyx.navigation.collections.ImmutableList
import com.bumble.appyx.navigation.collections.immutableListOf
import com.mycare.core.common.base.BaseState
import com.mycare.feature.appointments.presentation.model.AppointmentUiModel

internal data class AppointmentsState(
    val isLoading: Boolean = true,
    val user: String = "Jquelen",
    val upcomingAppointment: AppointmentUiModel? = null,
    val futureAppointments: ImmutableList<AppointmentUiModel> = immutableListOf(),
) : BaseState