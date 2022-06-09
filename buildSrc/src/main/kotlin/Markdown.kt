@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 26.04.2022.
 */
object Markdown {
    /**
     * @see <a href="https://mvnrepository.com/artifact/com.vladsch.flexmark/flexmark"> Flexmark</a>
     */
    object Flexmark {
        private const val version = "0.64.0"
        private const val lib = "com.vladsch.flexmark"

        const val core = "$lib:flexmark:$version"
        const val wikilink = "$lib:flexmark-ext-wikilink:$version"
    }
}
