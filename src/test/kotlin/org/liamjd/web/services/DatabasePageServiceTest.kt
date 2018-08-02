package org.liamjd.web.services

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*
import kotlin.test.assertEquals

object Blocks : LongIdTable() {
	val refName = varchar("refName", 255).index(isUnique = true)
	val sourceText = text("source")
	val uuid = uuid("uuid").clientDefault { UUID.randomUUID() }

	val page = reference("page", Pages)	// block hasOne page
}

class Block(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<Block>(Blocks)

	var refName by Blocks.refName
	var source by Blocks.sourceText
	var uuid by Blocks.uuid

	var page by Page referencedOn Blocks.page		// block hasOne page
}

object Pages : LongIdTable() {
	val refName = varchar("refName", 255).index(isUnique = true)
	val uuid = uuid("uuid").clientDefault { UUID.randomUUID() }
	val dirty = bool("dirty").default(false)

	val pgTemplate = reference("pgTemplate", PageTemplates).nullable()		// page has zero ore more blocks
}

class Page(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<Page>(Pages)

	var refName by Pages.refName
	var uuid by Pages.uuid
	var dirty by Pages.dirty

	val blocks by Block referrersOn Blocks.page		// page hasMany blocks
	var template by PageTemplate optionalReferencedOn Pages.pgTemplate		// page may have one template
}

object PageTemplates : LongIdTable() {
	val refName = varchar("refName", 255).index(isUnique = true)
	val uuid = uuid("uuid").clientDefault { UUID.randomUUID() }
	val templateSource = text("templateSource")

}

class PageTemplate(id: EntityID<Long>) : LongEntity(id) {
	companion object : LongEntityClass<PageTemplate>(PageTemplates)

	var refName by PageTemplates.refName
	var uuid by PageTemplates.uuid
	var templateSource by PageTemplates.templateSource

	val pages by Page optionalReferrersOn Pages.pgTemplate		// pageTemplate may have many pages
}

class DatabasePageServiceTest : Spek({

	val service = FakePageService()

	describe("Saving a page to the database") {
		it("Can save a simple page") {
			transaction {
				val home = Page.new {
					refName = "homePage"
				}

				val foundHome = Page.find { Pages.refName eq "homePage" }.first()
				assertEquals("homePage", foundHome.refName)
				assertEquals(true, foundHome.blocks.empty())
			}
		}
		it("Can save a page with a block") {
			transaction {
				val aboutPage = Page.new {
					refName = "aboutPage"
				}
				val aBlock = Block.new {
					refName = "myBlock"
					source = "This is a simple block"
					page = aboutPage
				}

				val foundAbout = Page.find { Pages.refName eq "aboutPage" }.first()
				assertEquals("aboutPage", foundAbout.refName)
				assertEquals(1, foundAbout.blocks.count())
				assertEquals("myBlock", foundAbout.blocks.first().refName)
			}
		}
		it("Can save a page with a block and a template") {
			transaction {
				val testTemplate = PageTemplate.new {
					refName = "pgTemplate"
					templateSource = "<b>A page template</b>"
				}
				val bigPage = Page.new {
					refName = "bigPage"
					template = testTemplate
				}
				val bigBlock = Block.new {
					refName = "bigBlock"
					source = "This is a big complicated block of text"
					page = bigPage
				}

				val foundBig = Page.find { Pages.refName eq "bigPage" }.first()
				assertEquals("bigPage", foundBig.refName)
				assertEquals(1, foundBig.blocks.count())
				assertEquals("pgTemplate", foundBig.template?.refName)
			}
		}
		afterGroup {
			println("after group, delete now?")
			transaction {
				Block.all().forEach { it.delete() }
				Page.all().forEach { it.delete() }
				PageTemplate.all().forEach { it.delete() }
			}
		}
	}
})


class DropTables : Spek({

	val service = FakePageService()

	describe("Clearing tables") {
		it("Deleting all the data") {
			transaction {
				Block.all().forEach { it.delete() }
				Page.all().forEach { it.delete() }
				PageTemplate.all().forEach { it.delete() }

				SchemaUtils.drop(Blocks, Pages, PageTemplates)
			}
		}
	}
})

class FakePageService {
	init {

		val dbConnectionString: String = pagedbConnections.connectionString
		val dbName: String = pagedbConnections.dbDatabase
		val dbUser: String = pagedbConnections.dbUser
		val dbPassword: String = pagedbConnections.dbPassword
		val dbDriver: String = pagedbConnections.driverClass

		println("Connecting to DB: ${dbConnectionString}${dbName}")  // ?user=${dbUser}&password=${dbPassword}
		println("Using driver: ${dbDriver}")
		Database.connect(url = dbConnectionString + dbName, user = dbUser, password = dbPassword, driver = dbDriver)

		transaction {
			SchemaUtils.createMissingTablesAndColumns(Blocks, PageTemplates, Pages)
			addLogger(StdOutSqlLogger)
		}
	}
}

object pagedbConnections {
	val connectionString = "jdbc:mysql://127.0.0.1:3306/"
	val driverClass = "org.mariadb.jdbc.Driver"
	val dbDatabase = "liamjdavison"
	val dbPassword = "indy25tlx"
	val dbUser = "liam"
}