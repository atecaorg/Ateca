@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 26.04.2022.
 */
object Markdown {
    /**
     * @see <a href="https://mvnrepository.com/artifact/com.vladsch.flexmark/flexmark"> Flexmark</a>
     */
    private const val version = "0.64.0"
    const val flexmark = "com.vladsch.flexmark:flexmark:$version"

    /**
     * @see <a href="https://github.com/commonmark/commonmark-java"> commonmark</a>
     */
    private const val versionMD = "0.18.1"
    const val commonmark = "org.commonmark:commonmark:$versionMD"
}