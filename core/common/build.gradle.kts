plugins {
    alias(libs.plugins.mycare.core)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.network)
            implementation(libs.androidx.lifecycle)
        }
    }
}

android {
    namespace = "com.mycare.core.common"
}
