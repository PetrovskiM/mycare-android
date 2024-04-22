package com.mycare.feature.appointments.presentation.contract

import com.bumble.appyx.navigation.collections.ImmutableList
import com.bumble.appyx.navigation.collections.immutableListOf
import com.mycare.core.common.base.BaseState
import com.mycare.core.common.base.ErrorState
import com.mycare.feature.appointments.common.presentation.model.AppointmentUiModel

internal data class AppointmentsState(
    override val isLoading: Boolean = true,
    val user: String = "Username",
    val upcomingAppointment: AppointmentUiModel? = null,
    val futureAppointments: ImmutableList<AppointmentUiModel> = immutableListOf(),
    override val error: ErrorState? = null,
) : BaseState
