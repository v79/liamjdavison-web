package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable

object PageTemplates : LongIdTable() {
	val refName = varchar("refName",length=255).index(isUnique = true)
	val fileName = varchar("fileName",length=255).index(isUnique=true)

}

class PageTemplateDB(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<PageTemplateDB>(PageTemplates)

	var refName by PageTemplates.refName
	var fileName by PageTemplates.fileName

	val pages by PageEntityDB referrersOn Pages.template
}