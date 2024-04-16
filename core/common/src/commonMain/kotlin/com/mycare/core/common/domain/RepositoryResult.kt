package com.mycare.core.common.domain

sealed interface RepositoryResult<out T> {
    data class Success<out T>(val data: T) : RepositoryResult<T>
    data class Failure(val throwable: Throwable) : RepositoryResult<Nothing>
}