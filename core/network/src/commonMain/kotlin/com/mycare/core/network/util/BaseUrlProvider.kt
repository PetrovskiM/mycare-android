package com.mycare.core.network.util

import com.mycare.core.network.model.Environment

object BaseUrlProvider {
    fun getEnvironmentBaseUrl(): String = if (PlatformUtil.isDebug) {
        Environment.DEV.url
    } else {
        Environment.PROD.url
    }
}