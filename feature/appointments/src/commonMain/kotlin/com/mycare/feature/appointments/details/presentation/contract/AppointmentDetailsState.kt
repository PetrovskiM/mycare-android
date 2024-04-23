package com.mycare.feature.appointments.details.presentation.contract

import com.mycare.core.common.base.BaseState
import com.mycare.core.common.base.ErrorState
import com.mycare.core.ui.presentation.model.AppointmentUiModel

internal data class AppointmentDetailsState(
    override val isLoading: Boolean = true,
    val appointment: AppointmentUiModel? = null,
    override val error: ErrorState? = null,
) : BaseState
