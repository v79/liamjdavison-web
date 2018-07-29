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

object BlockGroups : LongIdTable() {
	val refName = varchar("refName", length = 255).index(isUnique = true)
}

class BlockGroupEntity(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<BlockGroupEntity>(BlockGroups)

	var refName by BlockGroups.refName

	// blockgroup has 0 or more blocks
	var blocks by BlockEntity optionalReferencedOn Blocks.blockGroup

}

class BlockGroupDao : AbstractDao(), Dao {
	override val daoLogger: Logger
		get() = LoggerFactory.getLogger(BlockGroupDao::class.java)

	init {
		transaction {
			SchemaUtils.createMissingTablesAndColumns(BlockGroups)
			addLogger(StdOutSqlLogger)
		}
	}
}