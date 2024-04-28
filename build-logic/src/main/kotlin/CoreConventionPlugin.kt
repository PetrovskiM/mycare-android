import com.android.build.gradle.LibraryExtension
import com.mycare.configureKmp
import com.mycare.configureKotlinAndroid
import com.mycare.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CoreConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.mycare.spotless")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk =
                    libs.findVersion("android-targetSdk").get().requiredVersion.toInt()
                configureKotlinAndroid(this)
            }
            extensions.configure<KotlinMultiplatformExtension> {
                configureKmp(this)
                sourceSets.commonMain.dependencies {
                    implementation(libs.findLibrary("kotlinx.datetime").get())
                }
            }
        }
    }
}
