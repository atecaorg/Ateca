package com.ateca.domain.entity

import org.commonmark.Extension
import org.commonmark.node.Block
import org.commonmark.node.CustomBlock
import org.commonmark.node.CustomNode
import org.commonmark.parser.InlineParser
import org.commonmark.parser.Parser
import org.commonmark.parser.SourceLine
import org.commonmark.parser.block.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class YamlFrontMatterBlock : CustomBlock()

class YamlFrontMatterNode(var key: String, var values: List<String>) : CustomNode()

class YamlFrontMatterBlockParser : AbstractBlockParser() {
    private var inLiteral = false
    private var currentKey: String? = null
    private var currentValues: MutableList<String>
    private val block: YamlFrontMatterBlock

    init {
        currentValues = ArrayList()
        block = YamlFrontMatterBlock()
    }

    override fun getBlock(): Block {
        return block
    }

    override fun addLine(line: SourceLine?) {}

    override fun tryContinue(parserState: ParserState): BlockContinue {
        val line: CharSequence = parserState.line.content
        if (REGEX_END.matcher(line).matches()) {
            if (currentKey != null) {
                block.appendChild(YamlFrontMatterNode(currentKey!!, currentValues))
            }
            return BlockContinue.finished()
        }
        var matcher: Matcher = REGEX_METADATA.matcher(line)
        return if (matcher.matches()) {
            if (currentKey != null) {
                block.appendChild(YamlFrontMatterNode(currentKey!!, currentValues))
            }
            inLiteral = false
            currentKey = matcher.group(1)
            currentValues = ArrayList()
            if ("|" == matcher.group(2)) {
                inLiteral = true
            } else if ("" != matcher.group(2)) {
                currentValues.add(matcher.group(2))
            }
            BlockContinue.atIndex(parserState.index)
        } else {
            if (inLiteral) {
                matcher = REGEX_METADATA_LITERAL.matcher(line)
                if (matcher.matches()) {
                    if (currentValues.size == 1) {
                        currentValues[0] = """
                            ${currentValues[0]}
                            ${matcher.group(1).trim()}
                            """.trimIndent()
                    } else {
                        currentValues.add(matcher.group(1).trim())
                    }
                }
            } else {
                matcher = REGEX_METADATA_LIST.matcher(line)
                if (matcher.matches()) {
                    currentValues.add(matcher.group(1))
                }
            }
            BlockContinue.atIndex(parserState.index)
        }
    }


    override fun parseInlines(inlineParser: InlineParser) {}

    class Factory : AbstractBlockParserFactory() {
        override fun tryStart(
            state: ParserState,
            matchedBlockParser: MatchedBlockParser
        ): BlockStart? {
            val line: CharSequence = state.line.content
            val nextNonSpace = state.nextNonSpaceIndex
            val matcher: Matcher = REGEX_BEGIN.matcher(line.subSequence(nextNonSpace, line.length))
            return if (matcher.find()) {
                BlockStart.of(YamlFrontMatterBlockParser()).atIndex(nextNonSpace)
            } else BlockStart.none()
        }
    }

    companion object {
        private val REGEX_METADATA: Pattern = Pattern.compile("^[ ]{0,3}([A-Za-z0-9._-]+):\\s*(.*)")
        private val REGEX_METADATA_LIST: Pattern = Pattern.compile("^[ ]+-\\s*(.*)")
        private val REGEX_METADATA_LITERAL: Pattern = Pattern.compile("^\\s*(.*)")
        private val REGEX_BEGIN: Pattern = Pattern.compile("^-{3}(\\s.*)?")
        private val REGEX_END: Pattern = Pattern.compile("^(-{3}|\\.{3})(\\s.*)?")
    }
}

class YamlFrontMatterBlockExtension private constructor() : Parser.ParserExtension {
    override fun extend(parserBuilder: Parser.Builder) {
        parserBuilder.customBlockParserFactory(YamlFrontMatterBlockParser.Factory())
    }

    companion object {
        fun create(): Extension {
            return YamlFrontMatterBlockExtension()
        }
    }
}