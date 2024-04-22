package com.mycare.feature.appointments.common.domain

import com.mycare.core.common.domain.RepositoryResult
import com.mycare.core.common.domain.toRepositoryResult
import com.mycare.feature.appointments.common.data.AppointmentsApi
import com.mycare.feature.appointments.common.domain.model.Appointment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

internal interface AppointmentsRepository {
    suspend fun getAppointments(): RepositoryResult<List<Appointment>>
    suspend fun getAppointment(id: String): RepositoryResult<Appointment?>
}

@Factory
internal class AppointmentsRepositoryImpl(
    private val api: AppointmentsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AppointmentsRepository {

    override suspend fun getAppointments(): RepositoryResult<List<Appointment>> =
        withContext(dispatcher) {
            api.getAppointments()
                .toRepositoryResult { it.toDomainModels() }
        }

    override suspend fun getAppointment(id: String): RepositoryResult<Appointment?> =
        withContext(dispatcher) {
            api.getAppointment(id = id)
                .toRepositoryResult { it?.toDomainModel() }
        }
}
