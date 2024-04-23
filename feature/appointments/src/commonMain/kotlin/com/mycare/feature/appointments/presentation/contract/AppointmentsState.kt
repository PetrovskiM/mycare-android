package com.mycare.feature.appointments.presentation.contract

import com.mycare.core.common.base.BaseState
import com.mycare.core.common.base.ErrorState
import com.mycare.core.ui.presentation.model.AppointmentUiModel
import com.mycare.core.ui.util.ImmutableList
import com.mycare.core.ui.util.immutableListOf

internal data class AppointmentsState(
    override val isLoading: Boolean = true,
    val user: String = "Username",
    val upcomingAppointment: AppointmentUiModel? = null,
    val futureAppointments: ImmutableList<AppointmentUiModel> = immutableListOf(),
    override val error: ErrorState? = null,
) : BaseState
