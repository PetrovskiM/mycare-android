package com.mycare.feature.appointments.details.presentation

import com.mycare.core.common.base.BaseViewModel
import com.mycare.core.common.base.ErrorState
import com.mycare.core.common.domain.onFailure
import com.mycare.core.common.domain.onSuccess
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsState
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsViewAction
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsViewAction.Retry
import com.mycare.feature.appointments.domain.AppointmentsRepository
import com.mycare.feature.appointments.presentation.toUiModel
import mycare.feature.appointments.generated.resources.Res
import mycare.feature.appointments.generated.resources.common_appointments
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@OptIn(ExperimentalResourceApi::class)
@Factory
internal class AppointmentDetailsViewModel internal constructor(
    @InjectedParam private val appointmentId: String,
    private val repository: AppointmentsRepository,
) : BaseViewModel<AppointmentDetailsState, AppointmentDetailsViewAction>() {

    init {
        loadAppointment()
    }

    override fun setInitialState(): AppointmentDetailsState = AppointmentDetailsState()

    override fun onViewAction(viewAction: AppointmentDetailsViewAction) {
        when (viewAction) {
            Retry -> loadAppointment()
        }
    }

    private fun loadAppointment() {
        launch {
            repository.getAppointment(id = appointmentId)
                .onSuccess {
                    updateState { state ->
                        state.copy(
                            isLoading = false,
                            appointment = it?.toUiModel(),
                        )
                    }
                }
                .onFailure(::handleError)
        }
    }

    override fun handleError(throwable: Throwable) {
        updateState { state ->
            state.copy(
                isLoading = false,
                error = ErrorState.OnScreen(message = Res.string.common_appointments),
            )
        }
    }
}
