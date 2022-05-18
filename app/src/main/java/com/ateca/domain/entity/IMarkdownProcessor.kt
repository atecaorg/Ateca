package com.ateca.domain.entity

interface IMarkdownProcessor {

    fun getTagSubstrings(text: String): List<String>

    fun getCrossLinkSubstrings(text: String): List<String>
}
