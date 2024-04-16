plugins {
    alias(libs.plugins.mycare.core)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets {

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        commonMain.get().kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        commonMain.dependencies {
            api(libs.ktor.client)
            api(libs.ktor.client.negotiation)
            api(libs.ktor.client.serialization)
            api(libs.ktor.client.logging)
            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "com.mycare.core.network"
}