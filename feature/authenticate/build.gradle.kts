plugins {
    alias(libs.plugins.mycare.feature)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.appyx.navigation)
            api(libs.appyx.components.backstack)
        }
    }
}

android {
    namespace = "com.mycare.feature.authentication"
}
