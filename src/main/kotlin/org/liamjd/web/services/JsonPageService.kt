package org.liamjd.web.services

import com.beust.klaxon.Klaxon
import com.beust.klaxon.json
import org.liamjd.web.model.Page
import java.io.File

class JsonPageService : PageService {

	val path = "src/test/resources/"
	val extension = ".page.json"
	override fun getPage(refName: String): Page? {

		val classLoader = this.javaClass.classLoader
		val jsonFile = File(path + refName + extension).inputStream()

		val page = Klaxon().parse<Page>(jsonFile)

		return page;
	}

	override fun save(page: Page): Boolean {

		val pageAsJson = Klaxon().toJsonString(page)

			File(path + page.refName + extension).writeBytes(pageAsJson.toByteArray())

		return true;

	}

	override fun countPages(): Int {
		return 0
	}
}