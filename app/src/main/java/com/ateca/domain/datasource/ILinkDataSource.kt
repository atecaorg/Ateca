package com.ateca.domain.datasource

import com.ateca.domain.models.Link
import com.ateca.domain.models.NoteId

interface ILinkDataSource {

    fun getLinksById(id: NoteId): List<Link>

    fun getBacklinksById(id: NoteId): List<Link>

    fun addLink(link: Link)

    fun addLinks(links: List<Link>)
}