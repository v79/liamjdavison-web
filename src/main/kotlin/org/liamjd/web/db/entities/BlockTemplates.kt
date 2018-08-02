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

/*
object BlockTemplates : LongIdTable() {
	val refName = varchar("refName", length=255).index(isUnique = true)

}

class BlockTemplateEntity(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<BlockEntity>(BlockTemplates)

	var refName by BlockTemplates.refName

}

class BlockTemplateDao : AbstractDao(), Dao {
	override val daoLogger: Logger
		get() = LoggerFactory.getLogger(BlockTemplateDao::class.java)

	init {
		transaction {
			SchemaUtils.createMissingTablesAndColumns(BlockTemplates)
			addLogger(StdOutSqlLogger) }
	}
}*/
