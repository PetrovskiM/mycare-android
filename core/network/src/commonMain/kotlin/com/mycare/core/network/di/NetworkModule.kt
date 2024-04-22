package com.mycare.core.network.di

import com.mycare.core.network.httpClient
import com.mycare.core.network.util.HttpLogger
import com.mycare.core.network.util.PlatformUtil
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
@ComponentScan("com.mycare.core.network")
val networkModule = module {
    single {
        httpClient {
            if (PlatformUtil.isDebug) {
                install(Logging) {
                    level = LogLevel.BODY
                    logger = HttpLogger
                }
            }
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        explicitNulls = false
                        prettyPrint = true
                    },
                )
            }
        }
    }
}
