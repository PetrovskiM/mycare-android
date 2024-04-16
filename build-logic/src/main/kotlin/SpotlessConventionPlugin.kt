import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")
            configureSpotless()
        }
    }

    private fun Project.configureSpotless() {
        extensions.configure<SpotlessExtension> {
            ratchetFrom("origin/develop")
            kotlin {
                target("**/*.kt")
                ktlint()
                    .editorConfigOverride(
                        mapOf(
                            "disabled_rules" to "filename",
                            "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                            "ij_kotlin_allow_trailing_comma" to "true",
                            "ktlint_function_naming_ignore_when_annotated_with" to "Composable, Test"
                        )
                    )
            }
        }
    }
}