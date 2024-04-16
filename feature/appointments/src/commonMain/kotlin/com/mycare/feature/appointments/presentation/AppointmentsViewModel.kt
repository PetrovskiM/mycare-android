package com.mycare.feature.appointments.presentation

import com.mycare.core.common.base.BaseViewModel
import com.mycare.core.common.domain.onSuccess
import com.mycare.feature.appointments.domain.AppointmentsRepository
import com.mycare.feature.appointments.presentation.contract.AppointmentsState
import com.mycare.feature.appointments.presentation.contract.AppointmentsViewAction
import org.koin.core.annotation.Single

@Single
internal class AppointmentsViewModel(private val repository: AppointmentsRepository) :
    BaseViewModel<AppointmentsState, AppointmentsViewAction>() {

    init {
        loadAppointments()
    }

    override fun setInitialState(): AppointmentsState = AppointmentsState()

    override fun onViewAction(viewAction: AppointmentsViewAction) {
        when (viewAction) {
            else -> {}
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
        }
    }
}
