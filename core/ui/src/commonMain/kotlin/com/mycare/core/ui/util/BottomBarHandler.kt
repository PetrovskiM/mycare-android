package com.mycare.core.ui.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Single

@Single
class BottomBarHandler {
    private val _isBottomBarVisible = MutableStateFlow(false)
    val isBottomBarVisible = _isBottomBarVisible.asStateFlow()

    suspend fun emit(isBottomBarVisible: Boolean) {
        _isBottomBarVisible.emit(isBottomBarVisible)
    }
}