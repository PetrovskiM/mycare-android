package com.mycare.core.ui.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Single

@Single
class InsetsHandler {
    private val _shouldInsetTop = MutableStateFlow(true)
    val shouldInsetTop = _shouldInsetTop.asStateFlow()

    suspend fun emit(shouldInsetTop: Boolean) {
        _shouldInsetTop.emit(shouldInsetTop)
    }
}
