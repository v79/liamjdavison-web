package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.*
import org.joda.time.DateTime
import java.time.LocalDateTime

object Base : LongIdTable() {
	val createdOn = datetime("createdOn")
	val lastUpdatedOn = datetime("lastUpdatedOn")
}

abstract class BaseTable(id: EntityID<Long>) : LongEntity(id) {

	var createdOn by Base.createdOn
	var lastUpdatedOn by Base.lastUpdatedOn

	init {
		EntityHook.subscribe { action ->
			if(action.changeType == EntityChangeType.Created) {
				println("******* BaseTable change type created found ***** ")

			}
		}
	}
}

