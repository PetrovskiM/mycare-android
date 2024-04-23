plugins {
    alias(libs.plugins.mycare.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.mycare.feature.appointments"
}
