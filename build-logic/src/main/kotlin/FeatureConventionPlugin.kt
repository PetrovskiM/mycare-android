import com.mycare.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.mycare.core")
                apply("com.mycare.compose")
                apply("com.mycare.koin")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain.dependencies {
                        api(project(":core:common"))
                        api(project(":core:ui"))
                        api(project(":core:network"))
                        implementation(libs.findLibrary("appyx.navigation").get())
                        implementation(libs.findLibrary("appyx.components.backstack").get())
                    }
                }
            }
        }
    }
}
