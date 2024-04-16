package com.mycare

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKmp(
    extension: KotlinMultiplatformExtension,
) {
    extension.apply {
        jvmToolchain(17)
        task("testClasses")
        androidTarget()
        iosArm64()
        iosX64()
        iosSimulatorArm64()
    }
}