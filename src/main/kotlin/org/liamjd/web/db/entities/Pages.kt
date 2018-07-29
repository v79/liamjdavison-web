package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.expandArgs
import org.jetbrains.exposed.sql.transactions.transaction
import org.liamjd.web.db.AbstractDao
import org.liamjd.web.db.Dao
import org.liamjd.web.model.Block
import org.liamjd.web.model.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Pages : LongIdTable() {
	val title = varchar("title",length=1023).index(isUnique = false)
	val refName = varchar("refName", length=255).index(isUnique = true)
	val sourceText = text("source")
	val dirty = bool("dirty")

	val template = reference("template",PageTemplates)
}

class PageEntityDB(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<PageEntityDB>(Pages)

	var title by Pages.title
	var refName by Pages.refName
	var sourceText by Pages.sourceText
	var dirty by Pages.dirty
	var template by PageTemplateDB referencedOn Pages.template
}

class PageEntityDao : AbstractDao(), Dao {

	override val daoLogger: Logger
		get() = LoggerFactory.getLogger(PageEntityDao::class.java)

	init {
		transaction { SchemaUtils.create(Pages)
		SchemaUtils.createMissingTablesAndColumns(Pages)}
	}

	fun countPages(): Int {
		val count = transaction {
			PageEntityDB.count()
		}
		return count
	}

	fun getPage(refName: String) : Page? {
		daoLogger.info("Searching for page '$refName'")
		val pageEntity = transaction {
			PageEntityDB.find { Pages.refName eq refName }.firstOrNull()
		}
		if(pageEntity != null) {
			return fromEntityDB(pageEntity)
		} else return null

	}

	fun savePage(page: Page): Long {
		val newId = transaction {
			val newEntity = PageEntityDB.new {
				this.refName = page.refName
				this.title = page.title
				// eek
				this.sourceText = page.blocks[0].source
				this.dirty = true
			}
			newEntity.id.value
		}
		return newId
	}

	private fun fromEntityDB(entity: PageEntityDB) : Page {
		val page = Page(entity.refName)
		page.title = entity.title
		page.templateName = entity.template.fileName
		// eeek!
		val block = Block("block1")
		block.source = entity.sourceText
		page.blocks.add(block)

		return page
	}
}
