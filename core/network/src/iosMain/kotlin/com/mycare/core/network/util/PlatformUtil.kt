package com.mycare.core.network.util

import kotlin.experimental.ExperimentalNativeApi

internal actual object PlatformUtil {
    @OptIn(ExperimentalNativeApi::class)
    actual val isDebug = Platform.isDebugBinary
}