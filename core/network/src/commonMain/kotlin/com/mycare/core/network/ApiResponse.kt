package com.mycare.core.network

sealed interface ApiResponse<out T> {
    class Success<out T>(val data: T) : ApiResponse<T>
    class Failure(val throwable: Throwable) : ApiResponse<Nothing>
}

inline fun <T> ApiResponse<T>.onSuccess(block: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success && data != null) {
        block(data)
    }
    return this
}

inline fun <T> ApiResponse<T>.onError(block: (Throwable) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Failure) {
        block(throwable)
    }
    return this
}

inline fun <T> ApiResponse<T>.safeExtract(fallback: () -> T): T {
    return if (this is ApiResponse.Success) {
        data
    } else {
        fallback()
    }
}

inline fun <T, R> ApiResponse<T>.map(mapper: (T) -> R): ApiResponse<R> {
    return when (this) {
        is ApiResponse.Success -> ApiResponse.Success(mapper(data))
        is ApiResponse.Failure -> ApiResponse.Failure(throwable)
    }
}