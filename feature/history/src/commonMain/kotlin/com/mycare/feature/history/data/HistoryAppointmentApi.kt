package com.mycare.feature.history.data

import com.mycare.core.common.data.model.AppointmentResponse
import com.mycare.core.common.domain.model.AppointmentStatus
import com.mycare.core.network.ApiResponse
import com.mycare.core.network.get
import com.mycare.feature.history.data.HistoryAppointmentsEndpoints.APPOINTMENTS
import com.mycare.feature.history.data.HistoryAppointmentsEndpoints.QueryParameters.LOCATION
import com.mycare.feature.history.data.HistoryAppointmentsEndpoints.QueryParameters.NAME
import com.mycare.feature.history.data.HistoryAppointmentsEndpoints.QueryParameters.STATUS
import io.ktor.client.HttpClient
import org.koin.core.annotation.Factory

internal interface HistoryAppointmentApi {
    suspend fun getAppointments(
        name: String? = null,
        location: String? = null,
        status: AppointmentStatus? = null,
    ): ApiResponse<List<AppointmentResponse>>
}

@Factory
internal class HistoryAppointmentsApiImpl(private val httpClient: HttpClient) :
    HistoryAppointmentApi {
    override suspend fun getAppointments(
        name: String?,
        location: String?,
        status: AppointmentStatus?,
    ): ApiResponse<List<AppointmentResponse>> =
        httpClient.get(
            endpoint = APPOINTMENTS,
            queryParameters = mapOf(
                NAME to name,
                LOCATION to location,
                STATUS to status?.name,
            ),
        )
}
