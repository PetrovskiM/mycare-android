package com.mycare.core.network.model

internal enum class Environment(val url: String) {
    PROD("http://10.0.2.2:3000/api"),
    DEV("http://10.0.2.2:3000/api"),
    //10.0.2.2 == localhost
}
