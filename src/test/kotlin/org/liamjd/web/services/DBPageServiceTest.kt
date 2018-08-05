package org.liamjd.web.services

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.liamjd.web.db.dao.PageDAO
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class DBPageServiceTest : Spek({

	val service = DBPageService()

	describe("Fetching pages from the database") {
		it("Can load the home page") {
			val homePage = service.getPage("home")
			assertNotNull(homePage) {
				assertEquals("Home Page",it.title)
				assertEquals("mockHomeTemplate",it.templateName)
				assertTrue(it.blockGroups.size > 0)
			}
		}
	}

})