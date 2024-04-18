import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.compose")

            extensions.configure<KotlinMultiplatformExtension> {
                configureCompose(this)
            }
        }
    }

    private fun Project.configureCompose(
        extension: KotlinMultiplatformExtension,
    ) {
        val compose = extensions.getByType<ComposeExtension>().dependencies
        extension.apply {
            sourceSets.apply {
                commonMain.dependencies {
                    api(compose.runtime)
                    api(compose.foundation)
                    api(compose.material3)
                    api(compose.ui)
                    implementation(compose.components.resources)
                }
            }
        }
    }
}