package com.mycare.core.common.base

interface BaseState {
    val isLoading: Boolean
    val error: ErrorState?
}
