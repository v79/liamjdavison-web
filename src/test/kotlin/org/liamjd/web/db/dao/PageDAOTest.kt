package org.liamjd.web.db.dao

import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PageDAOTest : Spek({
	val dao = PageDAO()

	describe("Fetch the home page") {
		it("Fetch the home page from the DB") {
			val homePageDTO = dao.getPage("home")
			assertNotNull(homePageDTO) {
				val blockGroups = dao.getBlockGroups(it)
				assertNotNull(blockGroups) {
					assertEquals(1, blockGroups.size)
				}
			}
		}
	}

	describe("Get total number of pages") {
		it("counts all pages") {
			transaction {
				val count = dao.countPages()
				assertEquals(2, count)
			}
		}
	}
})