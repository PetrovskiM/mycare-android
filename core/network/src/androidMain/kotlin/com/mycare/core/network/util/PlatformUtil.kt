package com.mycare.core.network.util

import com.mycare.core.network.BuildConfig

internal actual object PlatformUtil {
    actual val isDebug: Boolean = BuildConfig.DEBUG
}