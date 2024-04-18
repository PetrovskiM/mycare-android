plugins {
    alias(libs.plugins.mycare.core)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.network)
            implementation(libs.androidx.lifecycle)
            implementation(libs.compose.jetbrains.resources)
        }
    }
}

android {
    namespace = "com.mycare.core.common"
}
