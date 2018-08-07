package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class BlockTypes(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<BlockTypes>(BLOCK_TYPE)

	var refName by BLOCK_GROUP.refName
	var description by BLOCK_TYPE.description
}