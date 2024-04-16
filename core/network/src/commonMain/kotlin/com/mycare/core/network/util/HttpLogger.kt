package com.mycare.core.network.util

import io.ktor.client.plugins.logging.Logger

internal object HttpLogger : Logger {
    override fun log(message: String) {
        val messages = message.split("\n")
        messages.forEach {
            if (it.length > MAX_LINE_LENGTH) {
                var leftToShow = it.length
                var currentPosition = 0
                while (leftToShow > 0) {
                    println(
                        "$TAG: ${
                            it.substring(
                                currentPosition,
                                (currentPosition + MAX_LINE_LENGTH).coerceAtMost(it.length)
                            )
                        }"
                    )
                    leftToShow -= MAX_LINE_LENGTH
                    currentPosition += MAX_LINE_LENGTH
                }
            } else {
                println("$TAG: $it")
            }
        }
    }

    private const val MAX_LINE_LENGTH = 4000
    private const val TAG = "HTTP Client"
}