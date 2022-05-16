package com.ateca.data.local

import com.ateca.data.local.room.dao.LinkDao
import com.ateca.data.local.room.toEntity
import com.ateca.data.local.room.toModel
import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.models.Link
import com.ateca.domain.models.NoteId

/**
 * Created by dronpascal on 16.05.2022.
 */
class LinkDataSource(
    private val linkDao: LinkDao
) : ILinkDataSource {

    override fun getLinksById(id: NoteId): List<Link> =
        linkDao.select(id).toModel()

    override fun getBacklinksById(id: NoteId): List<Link> =
        linkDao.selectBacklinks(id).toModel()

    override fun addLink(link: Link) {
        linkDao.insert(link.toEntity())
    }

    override fun addLinks(links: List<Link>) {
        linkDao.insert(links.toEntity())
    }
}