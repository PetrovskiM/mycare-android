import com.mycare.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain.get().kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                    commonMain.dependencies {
                        implementation(libs.findLibrary("koin.core").get())
                        implementation(libs.findLibrary("koin.annotation").get())
                    }
                }
            }
        }
    }
}