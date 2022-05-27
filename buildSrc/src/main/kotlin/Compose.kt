@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 26.04.2022.
 */
object Compose {
    private const val activityComposeVersion = "1.4.0"
    const val activity = "androidx.activity:activity-compose:$activityComposeVersion"

    /**
     * @see <a href="https://developer.android.com/jetpack/androidx/releases/compose">Compose Doc</a>
     */
    const val version = "1.2.0-beta02"
    const val foundation = "androidx.compose.foundation:foundation:$version"
    const val layout = "androidx.compose.foundation:foundation-layout:$version"
    const val ui = "androidx.compose.ui:ui:$version"
    const val material = "androidx.compose.material:material:$version"
    const val materialIconsExtended =
        "androidx.compose.material:material-icons-extended:$version"
    const val uiText = "androidx.compose.ui:ui-text-google-fonts:$version"
    const val uiUtil = "androidx.compose.ui:ui-util:$version"
    const val viewBinding = "androidx.compose.ui:ui-viewbinding:$version"
    const val runtime = "androidx.compose.runtime:runtime:$version"
    const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"

    const val toolingDebug = "androidx.compose.ui:ui-tooling:$version"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"

    object ConstraintLayout {
        const val version = "1.0.1"
        const val composeConstraint = "androidx.constraintlayout:constraintlayout-compose:$version"
    }

    object Material3 {
        private const val version = "1.0.0-alpha10"

        const val material3 = "androidx.compose.material3:material3:$version"
    }

    /**
     * @see <a href="https://developer.android.com/jetpack/compose/navigation#setup">Navigation Doc</a>
     */
    private const val navigationVersion = "2.4.2"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    object Test {
        const val test = "androidx.compose.ui:ui-test:$version"
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
    }
}