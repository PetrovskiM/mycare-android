plugins {
    alias(libs.plugins.mycare.feature)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.google.maps)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.mycare.feature.appointments"
}
