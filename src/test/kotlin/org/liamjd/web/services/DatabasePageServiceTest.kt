package org.liamjd.web.services

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.liamjd.web.model.Page
import kotlin.test.assertTrue

class DatabasePageServiceTest : Spek({

	val page: Page = Page("saveMe")
	page.title = "Saved by the database"
	page.templateName = "home"

	val pageService = DBPageService()

	describe("Saving a page to the database") {
		it("Can save a simple page") {
			val savedPage = pageService.save(page)

			assertTrue(savedPage)
		}

	}
})