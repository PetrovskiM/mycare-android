plugins {
    `kotlin-dsl`
    alias(libs.plugins.ksp)
}

group = "com.mycare.build.logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.ksp.gradle)
    implementation(libs.spotless)
}

gradlePlugin {
    plugins {
        register("core") {
            id = "com.mycare.core"
            implementationClass = "CoreConventionPlugin"
        }
        register("spotless") {
            id = "com.mycare.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("koin") {
            id = "com.mycare.koin"
            implementationClass = "KoinConventionPlugin"
        }
        register("compose") {
            id = "com.mycare.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("feature") {
            id = "com.mycare.feature"
            implementationClass = "FeatureConventionPlugin"
        }
    }
}