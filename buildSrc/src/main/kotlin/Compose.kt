/**
 * Created by dronpascal on 26.04.2022.
 */
object Compose {
    private const val activityComposeVersion = "1.4.0"
    const val activity = "androidx.activity:activity-compose:$activityComposeVersion"

    /**
     * @see <a href="https://developer.android.com/jetpack/androidx/releases/compose">Compose Doc</a>
     */
    const val composeVersion = "1.1.1"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val toolingDebug = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val icons = "androidx.compose.material:material-icons-extended:$composeVersion"

    /**
     * @see <a href="https://developer.android.com/jetpack/compose/navigation#setup">Navigation Doc</a>
     */
    private const val navigationVersion = "2.4.2"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val constraintLayoutVersion = "1.0.0"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${constraintLayoutVersion}"

    object Test {
        const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
    }
}