package org.liamjd.web.services

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.liamjd.web.db.AbstractDao
import org.liamjd.web.db.entities.*

class MockData : Spek({

	val mockDao = MockDao()

	var pageTemplate: PageTemplates? = null
	var homePage: Pages? = null
	var aboutPage: Pages? = null
	var homePageBlocks = setOf<Blocks>()
	var aboutPageBlocks = setOf<Blocks>()
	var homePageGroup: BlockGroups? = null
	var aboutPageGroup: BlockGroups? = null
	var blockTemplateA: BlockTemplates? = null
	var blockTemplateB: BlockTemplates? = null

	beforeGroup {
		transaction {
			addLogger(StdOutSqlLogger)
			SchemaUtils.drop(PAGE, PAGE_TEMPLATE, BLOCK, BLOCK_TEMPLATE, BLOCK_GROUP)
			SchemaUtils.create(PAGE, PAGE_TEMPLATE, BLOCK, BLOCK_TEMPLATE, BLOCK_GROUP)
		}
	}

	describe("Populate some sample data") {
		it("set up the templates") {
			transaction {
				addLogger(StdOutSqlLogger)
				PageTemplates.new {
					refName = "mockHomeTemplate"
					sourceText = "<b>The home page template!</b>"
				}

				blockTemplateA = BlockTemplates.new {
					refName = "mockBlockTemplate_A"
					sourceText = "block template alpha"
				}
				blockTemplateA = BlockTemplates.new {
					refName = "mockBlockTemplate_B"
					sourceText = "block template beta"
				}

				pageTemplate = PageTemplates.all().first()
			}
		}
		it("create a couple of pages") {
			transaction {
				addLogger(StdOutSqlLogger)
				homePage = Pages.new {
					refName = "home"
					template = pageTemplate!!
					title = "Home Page"
					dirty = false
				}
				aboutPage = Pages.new {
					refName = "about"
					template = pageTemplate!!
					title = "About Me"
					dirty = false
				}
			}
		}

		it("add a couple of blockgroups") {
			transaction {
				addLogger(StdOutSqlLogger)
				homePageGroup = BlockGroups.new {
					refName = "homePageGroup"
					page = homePage!!
				}
				aboutPageGroup = BlockGroups.new {
					refName = "aboutPageGroup"
					page = aboutPage!!
				}
			}
		}

		it("add some blocks to the pages") {
			transaction {

				blockTemplateA = BlockTemplates.find { BLOCK_TEMPLATE.refName eq "mockBlockTemplate_A"}.first()
				blockTemplateB = BlockTemplates.find { BLOCK_TEMPLATE.refName eq "mockBlockTemplate_B"}.first()

				addLogger(StdOutSqlLogger)
				for(i in 1..10) {
					Blocks.new {
						refName = "homePageBlock_$i"
						page = homePage!!
						content = "This is block $i"
						template = blockTemplateA!!
					}
				}
//				homePageBlocks  = Blocks.find { BLOCK.refName like "%homePageBlock%"}.toSet()

				for(i in 50..55) {
					Blocks.new {
						refName = "aboutPageBlock_$i"
						page = aboutPage!!
						content = "This is block $i"
						template = blockTemplateB!!
					}
				}
//				aboutPageBlocks  = Blocks.find { BLOCK.refName like "%aboutPageBlock%"}.toSet()
			}
		}

		it("add some blocks to the groups") {
			transaction {
				addLogger(StdOutSqlLogger)
				for(i in 217..229) {
					Blocks.new {
						refName = "homePageGroupBlock_$i"
						page = homePage!!
						group = homePageGroup!!
						content = "This is block $i"
						template = blockTemplateB!!
					}
				}

				for(i in 5141..5149) {
					Blocks.new {
						refName = "aboutPageGroupBlock_$i"
						page = aboutPage!!
						group = aboutPageGroup!!
						content = "This is block $i"
						template = blockTemplateA!!
					}
				}
			}
		}
	}
})

class MockDao : AbstractDao() {

}