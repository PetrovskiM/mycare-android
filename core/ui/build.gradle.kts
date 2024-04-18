plugins {
    alias(libs.plugins.mycare.core)
    alias(libs.plugins.mycare.compose)
    alias(libs.plugins.mycare.koin)
}

android {
    namespace = "com.mycare.core.ui"

}

dependencies {
    implementation(libs.androidx.core.ktx)
}
