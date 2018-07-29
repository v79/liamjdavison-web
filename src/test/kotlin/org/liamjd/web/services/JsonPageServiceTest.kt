package org.liamjd.web.services

import com.beust.klaxon.Klaxon
import com.beust.klaxon.json
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.liamjd.web.model.Page
import spark.kotlin.patch
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class JsonPageServiceTest : Spek({

	val emptyPage = Page("empty")
	emptyPage.title = "Empty Page"
	emptyPage.templateName = "empty-template"

	var service = JsonPageService()

	describe("saving pages to Json") {
		it("can convert an empty page to Json") {

			val result = service.save(emptyPage)
			assertTrue(result)

		}
	}

	describe("loading pages from Json") {
		it ("can load an empty page from Json") {
			val page = service.getPage("empty")
			assertNotNull(page) {
				assertEquals("empty", it.refName)
				assertEquals("Empty Page", it.title)
			}
		}
	}

})