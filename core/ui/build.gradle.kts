import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    alias(libs.plugins.mycare.core)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ksp)
}

kotlin {

    sourceSets {
        commonMain.get().kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.annotations)
        }
    }
}

android {
    namespace = "com.mycare.core.ui"

}

dependencies {
    implementation(libs.androidx.core.ktx)
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
