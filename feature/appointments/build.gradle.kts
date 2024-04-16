import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    alias(libs.plugins.mycare.feature)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.get().kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.annotations)
            implementation(libs.appyx.navigation)
            implementation(libs.appyx.components.backstack)
            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.mycare.feature.appointments"
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    // DO NOT add bellow dependencies
//    add("kspAndroid", Deps.Koin.kspCompiler)
//    add("kspIosX64", Deps.Koin.kspCompiler)
//    add("kspIosArm64", Deps.Koin.kspCompiler)
//    add("kspIosSimulatorArm64", Deps.Koin.kspCompiler)
}

// WORKAROUND: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
tasks.withType<KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

