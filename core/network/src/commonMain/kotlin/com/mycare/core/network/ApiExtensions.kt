package com.mycare.core.network

import com.mycare.core.network.util.BaseUrlProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend inline fun <reified T> HttpClient.get(
    endpoint: String,
    vararg pathSegments: String,
    baseUrl: String = BaseUrlProvider.getEnvironmentBaseUrl(),
    queryParameters: Map<String, String?> = emptyMap(),
): ApiResponse<T> = try {
    get("$baseUrl${getPathAdjustedEndpoint(endpoint, *pathSegments)}") {
        queryParameters.forEach { parameter(it.key, it.value) }
        contentType(ContentType.Application.Json)
    }.toResponse()
} catch (e: Throwable) {
    ApiResponse.Failure(e)
}

suspend inline fun <reified T, reified R> HttpClient.post(
    endpoint: String,
    vararg pathSegments: String,
    baseUrl: String = BaseUrlProvider.getEnvironmentBaseUrl(),
    queryParameters: Map<String, String?> = emptyMap(),
    body: R,
): ApiResponse<T> = try {
    post("$baseUrl${getPathAdjustedEndpoint(endpoint, *pathSegments)}") {
        contentType(ContentType.Application.Json)
        queryParameters.forEach { parameter(it.key, it.value) }
        setBody(body)
    }.toResponse()
} catch (e: Throwable) {
    ApiResponse.Failure(e)
}

suspend inline fun <reified T> HttpClient.delete(
    endpoint: String,
    vararg pathSegments: String,
    baseUrl: String = BaseUrlProvider.getEnvironmentBaseUrl(),
    queryParameters: Map<String, String?> = emptyMap(),
): ApiResponse<T> = try {
    delete("$baseUrl${getPathAdjustedEndpoint(endpoint, *pathSegments)}") {
        contentType(ContentType.Application.Json)
        queryParameters.forEach { parameter(it.key, it.value) }
        setBody(body)
    }.toResponse()
} catch (e: Throwable) {
    ApiResponse.Failure(e)
}

fun getPathAdjustedEndpoint(
    endpoint: String,
    vararg pathSegments: String,
) = if (pathSegments.isNotEmpty()) {
    endpoint.let {
        var updatedEndpoint = endpoint
        pathSegments.forEach { segment ->
            val startIndex =
                updatedEndpoint.indexOfFirst { it.toString() == START_PATH_SEGMENT }
            val endIndex =
                updatedEndpoint.indexOfFirst { it.toString() == END_PATH_SEGMENT }
            updatedEndpoint =
                updatedEndpoint.replaceRange(startIndex..endIndex, segment)
        }
        updatedEndpoint
    }
} else {
    endpoint
}

private const val START_PATH_SEGMENT = "{"
private const val END_PATH_SEGMENT = "}"