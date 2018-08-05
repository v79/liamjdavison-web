package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class PageTemplates(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<PageTemplates>(PAGE_TEMPLATE)

	var refName by PAGE_TEMPLATE.refName
	var uuid by PAGE_TEMPLATE.uuid
	var createdOn by PAGE_TEMPLATE.createdOn
	var lastUpdated by PAGE_TEMPLATE.lastUpdated

	var sourceText by PAGE_TEMPLATE.sourceText
}