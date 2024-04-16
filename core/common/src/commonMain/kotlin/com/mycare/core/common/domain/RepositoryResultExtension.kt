package com.mycare.core.common.domain

import com.mycare.core.network.ApiResponse

suspend fun <T, R> ApiResponse<T>.toRepositoryResult(map: suspend (T) -> R): RepositoryResult<R> {
    return if (this is ApiResponse.Success) {
        try {
            RepositoryResult.Success(map(this.data))
        } catch (e: Exception) {
            RepositoryResult.Failure(e)
        }
    } else {
        RepositoryResult.Failure((this as ApiResponse.Failure).throwable)
    }
}

fun <T> RepositoryResult<T>.onSuccess(action: (T) -> Unit): RepositoryResult<T> {
    if (this is RepositoryResult.Success) {
        action(this.data)
    }
    return this
}

fun <T> RepositoryResult<T>.onFailure(action: (Throwable) -> Unit): RepositoryResult<T> {
    if (this is RepositoryResult.Failure) {
        action(this.throwable)
    }
    return this
}