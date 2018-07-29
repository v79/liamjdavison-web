package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.liamjd.web.db.AbstractDao
import org.liamjd.web.db.Dao
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Blocks : LongIdTable() {
	val refName = varchar("refName", length=255).index(isUnique = true)
	val sourceText = text("source")
	val uuid = uuid("uuid")

	val page = optReference("page", Pages)
	val template = reference("template",BlockTemplates)
	val blockGroup = optReference("blockGroup",BlockGroups)
}

class BlockEntity(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<BlockEntity>(Blocks)

	var refName by Blocks.refName
	var sourceText by Blocks.sourceText

	var template by BlockEntity referencedOn Blocks.template
//	var page by PageEntity optionalReferencedOn Pages.blocks
}

class BlockDao : AbstractDao(), Dao {
	override val daoLogger: Logger
		get() = LoggerFactory.getLogger(BlockDao::class.java)

	init {
		transaction {
			SchemaUtils.createMissingTablesAndColumns(Blocks)
			addLogger(StdOutSqlLogger) }
	}
}
