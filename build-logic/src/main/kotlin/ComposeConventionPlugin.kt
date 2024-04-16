import com.mycare.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.withPlugin(libs.findPlugin("jetbrainsCompose").get().get().pluginId) {

            }
            pluginManager.apply("org.jetbrains.compose")

            extensions.configure<KotlinMultiplatformExtension> {
                configureCompose(this)
            }
        }
    }

    private fun Project.configureCompose(
        extension: KotlinMultiplatformExtension,
    ) {
        extension.apply {
            sourceSets.apply {
                commonMain.get().kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                commonMain.dependencies {
                   // api(compose.runtime)
                   // api(composeDeps.compose.foundation)
                   // api(composeDeps.compose.material3)
                   // api(composeDeps.compose.ui)
                   // implementation(composeDeps.compose.components.resources)
                }
            }
        }
    }
}