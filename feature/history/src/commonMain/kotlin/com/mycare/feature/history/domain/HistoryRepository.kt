package com.mycare.feature.history.domain

import com.mycare.core.common.domain.RepositoryResult
import com.mycare.core.common.domain.model.AppointmentStatus
import com.mycare.core.common.domain.toRepositoryResult
import com.mycare.feature.history.data.HistoryAppointmentApi
import com.mycare.feature.history.domain.model.HistoryAppointment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

internal interface HistoryRepository {
    suspend fun getAppointments(
        name: String? = null,
        location: String? = null,
        status: AppointmentStatus? = null,
    ): RepositoryResult<List<HistoryAppointment>>
}

@Factory
internal class HistoryRepositoryImpl(
    private val api: HistoryAppointmentApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : HistoryRepository {
    override suspend fun getAppointments(
        name: String?,
        location: String?,
        status: AppointmentStatus?,
    ): RepositoryResult<List<HistoryAppointment>> = withContext(dispatcher) {
        api.getAppointments(
            name = name,
            location = location,
            status = status,
        ).toRepositoryResult {
            it.toDomainModels()
        }
    }
}
