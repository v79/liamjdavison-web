package org.liamjd.web.db.entities

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.EntityHook.subscribe
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.liamjd.web.db.AbstractDao
import org.liamjd.web.db.Dao
import org.liamjd.web.model.Block
import org.liamjd.web.model.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

/**
 * In the Object, declare all the columns of the table
 * Foreign keys are 'reference' or 'optReference' if nullable
 */
/*
object Pages : LongIdTable() {
	val createdOn = datetime("createdOn").default(DateTime.now())
	val title = varchar("title", length = 1023).index(isUnique = false)
	val refName = varchar("refName", length = 255).index(isUnique = true)
//	val sourceText = text("source")
	val dirty = bool("dirty")

//	val template = reference("template", PageTemplates)
//	val blocks = reference("blocks", Blocks)
}

*/
/**
 * In the class, all the columns are vars by Object
 * Then list all the foreign get references using 'referencedOn' and 'optionalReferencedOn'
 *//*

class PageEntity(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<PageEntity>(Pages)

	var createdOn by Pages.createdOn
	var title by Pages.title
	var refName by Pages.refName
//	var sourceText by Pages.sourceText
	var dirty by Pages.dirty

	// page has 1 template
//	var template by PageTemplateDB referencedOn Pages.template

	// page has 0 or more blocks
//	var blocks by BlockEntity referencedOn Pages.blocks


	init {
		subscribe { action ->
			if (action.changeType == EntityChangeType.Created) {
				println("¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬ saving ${action.entityClass} in db \"¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬")
			}
		}
	}



}

class PageEntityDao : AbstractDao(), Dao {

	override val daoLogger: Logger
		get() = LoggerFactory.getLogger(PageEntityDao::class.java)

	val pageTemplateDao = PageTemplateDao()

	init {
		transaction {
			SchemaUtils.createMissingTablesAndColumns(Pages)
			addLogger(StdOutSqlLogger)
		}
	}

	fun countPages(): Int {
		val count = transaction {
			PageEntity.count()
		}
		return count
	}

	fun getPage(refName: String): Page? {
		daoLogger.info("Searching for page '$refName'")
		val pageEntity = transaction {
			PageEntity.find { Pages.refName eq refName }.firstOrNull()
		}
		if (pageEntity != null) {
			return fromEntityDB(pageEntity)
		}
		return null
	}

	fun savePage(page: Page): Long {
		daoLogger.info("Saving page '${page.refName}'")
		val newId = transaction {
			val newEntity = PageEntity.new {
				refName = page.refName
				title = page.title
				// eek
//				this.sourceText = page.blocks[0].source
				dirty = true


			}
			newEntity.id.value
		}
		return newId
	}

	private fun fromEntityDB(entity: PageEntity): Page {
		val page = Page(entity.refName)
		page.title = entity.title

		//TODO is thiis how I really want to do this?
		*/
/*page.templateName = transaction {
			pageTemplateDao.getTemplateFilename(entity.template.refName)
		}*//*

		// eeek!
		val block = Block("block1")
//		block.source = entity.sourceText
		page.blocks.add(block)

		return page
	}
}
*/


