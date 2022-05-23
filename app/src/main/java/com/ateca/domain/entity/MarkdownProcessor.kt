package com.ateca.domain.entity

/**
 * Created by dronpascal on 18.05.2022.
 */
class MarkdownProcessor : IMarkdownProcessor {

    override fun getTagSubstrings(text: String): List<String> =
        Regex(TAG_REGEX)
            .findAll(text)
            .map {
                it.value
                    .replace(Regex(TAG_TRASH_SYMBOLS_REGEX), "")
            }
            .toList()

    override fun getCrossLinkSubstrings(text: String): List<String> =
        Regex(CROSS_LINK_REGEX)
            .findAll(text)
            .map {
                it.value
                    .split("|")
                    .last()
                    .replace(Regex(LINK_TRASH_SYMBOLS_REGEX), "")
            }
            .toList()

    private companion object Regexps {
        /**
         * @see <a href="https://regexr.com/"> Regexp check</a>
         */
        // Matches: #sample, [Tab]#sample, [\n]#sample, [space]#sample
        // To extract pure tag we have to remove all [` `,`\n`,`\t`,`#`] symbols
        const val TAG_REGEX =
            """(^| |\n|\t)(#{1}([^~!@#'$'{}%^&*()=+`'\-\|\\\/\[\]\{\}\t\n\r ])+)"""
        const val TAG_TRASH_SYMBOLS_REGEX = """[ \n\t#]"""

        // Matches: [[sample]], [[sample|spl]]
        // To extract pure link name we have to split by '|',
        // select last element and remove all '[[',']]' substrings
        // TODO: Link can contain single '[' or ']'. Example '[[t]e[s]t]]'.
        const val CROSS_LINK_REGEX = """\[{2}([^\[\]\|]+)\]{2}"""
        const val LINK_TRASH_SYMBOLS_REGEX = """\[{2}|\]{2}"""
    }
}