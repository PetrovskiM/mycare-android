package com.mycare.core.network

import com.mycare.core.network.model.ApiError
import com.mycare.core.network.model.ApiException
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode.Companion.MultiStatus
import io.ktor.http.HttpStatusCode.Companion.OK


suspend inline fun <reified T> HttpResponse.toResponse(): ApiResponse<T> {
    return if (status in OK..MultiStatus) {
        ApiResponse.Success(body())
    } else {
        ApiResponse.Failure(getOrDefaultError(this))
    }
}

suspend inline fun getOrDefaultError(response: HttpResponse): ApiException = try {
    val errorMessage = response.body<ApiError>()
    ApiException(error = errorMessage.error)
} catch (e: NoTransformationFoundException) {
    ApiException()
}