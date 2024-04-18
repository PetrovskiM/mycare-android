plugins {
    alias(libs.plugins.mycare.core)
    alias(libs.plugins.serialization)
    alias(libs.plugins.mycare.koin)
}

kotlin {
    sourceSets {

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        commonMain.dependencies {
            api(libs.ktor.client)
            api(libs.ktor.client.negotiation)
            api(libs.ktor.client.serialization)
            api(libs.ktor.client.logging)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "com.mycare.core.network"
}