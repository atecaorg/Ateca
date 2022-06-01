package com.ateca.domain.entity

import org.commonmark.Extension
import org.commonmark.node.*
import org.commonmark.parser.Parser
import org.commonmark.parser.block.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class NoteAtecaNode(var title: String, var destination: String? = null) : CustomNode()


class NoteAtecaBlock(private var title: String, private var destination: String? = null) :
    CustomBlock() {
    fun getTitle() = title
    fun getDestination() = destination
}

class NoteAtecaBlockParser(private var noteAtecaBlock: NoteAtecaBlock) : AbstractBlockParser() {

    private val block: NoteAtecaBlock = noteAtecaBlock

    override fun getBlock(): Block {
        return block
    }

    override fun tryContinue(parserState: ParserState?): BlockContinue {
        return BlockContinue.none()
    }

    class Factory : AbstractBlockParserFactory() {
        override fun tryStart(
            state: ParserState,
            matchedBlockParser: MatchedBlockParser
        ): BlockStart? {

            val line: CharSequence = state.line.content
            val nextNonSpace = state.nextNonSpaceIndex
            val matcher: Matcher =
                REGEX_METADATA.matcher(line.subSequence(nextNonSpace, line.length))
            return if (matcher.find()) {
                val title = matcher.group(1)
                var destination: String? = null
                if (!matcher.group(2).isNullOrEmpty()) destination = matcher.group(2)
                val block = NoteAtecaBlock(title!!, destination)
                val link = Link(destination, title)
                link.appendChild(Text(title))
                block.prependChild(link)
                BlockStart.of(NoteAtecaBlockParser(block)).atColumn(line.length)
            } else BlockStart.none()
        }
    }

    companion object {
        private val REGEX_METADATA: Pattern = Pattern.compile("""^\[{2}([^\\]+)\|([^\\]+)*\]{2}""")
    }
}

class NoteAtecaBlockExtension private constructor() : Parser.ParserExtension {
    override fun extend(parserBuilder: Parser.Builder) {
        parserBuilder.customBlockParserFactory(NoteAtecaBlockParser.Factory())
    }

    companion object {
        fun create(): Extension {
            return NoteAtecaBlockExtension()
        }
    }
}