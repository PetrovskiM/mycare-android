package com.mycare.feature.appointments.presentation

import com.mycare.core.common.base.BaseViewModel
import com.mycare.core.common.base.ErrorState
import com.mycare.core.common.domain.onFailure
import com.mycare.core.common.domain.onSuccess
import com.mycare.feature.appointments.domain.AppointmentsRepository
import com.mycare.feature.appointments.presentation.contract.AppointmentsState
import com.mycare.feature.appointments.presentation.contract.AppointmentsViewAction
import com.mycare.feature.appointments.presentation.contract.AppointmentsViewAction.Retry
import mycare.feature.appointments.generated.resources.Res
import mycare.feature.appointments.generated.resources.common_appointments
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.annotation.Single

@Single
@OptIn(ExperimentalResourceApi::class)
internal class AppointmentsViewModel(private val repository: AppointmentsRepository) :
    BaseViewModel<AppointmentsState, AppointmentsViewAction>() {

    init {
        loadAppointments()
    }

    override fun setInitialState(): AppointmentsState = AppointmentsState()

    override fun onViewAction(viewAction: AppointmentsViewAction) {
        when (viewAction) {
            Retry -> loadAppointments()
        }
    }

    private fun loadAppointments() {
        launch {
            repository.getAppointments()
                .onSuccess { appointments ->
                    val (upcomingAppointment, futureAppointments) = appointments.toUiModels()
                    updateState { state ->
                        state.copy(
                            isLoading = false,
                            upcomingAppointment = upcomingAppointment,
                            futureAppointments = futureAppointments,
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
                error = ErrorState.OnScreen(Res.string.common_appointments),
            )
        }
    }
}
