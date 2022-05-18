package com.ateca.domain.datasource

import com.ateca.domain.models.Link
import com.ateca.domain.models.NoteId

interface ILinkDataSource {

    suspend fun getLinksById(id: NoteId): List<Link>

    suspend fun getBacklinksById(id: NoteId): List<Link>

    suspend fun addLink(link: Link)

    suspend fun addLinks(links: List<Link>)
}