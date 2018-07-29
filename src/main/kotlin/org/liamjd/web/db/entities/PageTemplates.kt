package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.liamjd.web.db.AbstractDao
import org.liamjd.web.db.Dao
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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

class PageTemplateDao : AbstractDao(), Dao {
	override val daoLogger: Logger
		get() = LoggerFactory.getLogger(PageTemplateDao::class.java)

	init {
		transaction {
			addLogger(StdOutSqlLogger)
			SchemaUtils.create(PageTemplates)
			SchemaUtils.createMissingTablesAndColumns(PageTemplates)
		}
	}

	fun getTemplateFilename(refName: String) : String {
		val pageTemplateEntity = transaction {
			PageTemplateDB.find { PageTemplates.refName eq refName}.firstOrNull()
		}
		if(pageTemplateEntity != null) {
			return pageTemplateEntity.fileName
		}
		return ""
	}
}