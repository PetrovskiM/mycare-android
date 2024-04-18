@file:OptIn(ExperimentalResourceApi::class)

package com.mycare.core.common.base

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

sealed interface ErrorState {
    data class OnScreen(val message: StringResource) : ErrorState
}
