package com.mycare.feature.appointments.data

import com.mycare.core.network.ApiResponse
import com.mycare.core.network.get
import com.mycare.feature.appointments.data.AppointmentsEndpoints.APPOINTMENT
import com.mycare.feature.appointments.data.AppointmentsEndpoints.APPOINTMENTS
import com.mycare.feature.appointments.data.model.AppointmentResponse
import io.ktor.client.HttpClient
import org.koin.core.annotation.Factory

internal interface AppointmentsApi {
    suspend fun getAppointments(): ApiResponse<List<AppointmentResponse>>
    suspend fun getAppointment(id: String): ApiResponse<AppointmentResponse?>
}

@Factory
internal class AppointmentsApiImpl(private val httpClient: HttpClient) : AppointmentsApi {
    override suspend fun getAppointments(): ApiResponse<List<AppointmentResponse>> =
        httpClient.get(endpoint = APPOINTMENTS)

    override suspend fun getAppointment(id: String): ApiResponse<AppointmentResponse> =
        httpClient.get(
            endpoint = APPOINTMENT,
            /*pathSegments = */ id,
        )
}