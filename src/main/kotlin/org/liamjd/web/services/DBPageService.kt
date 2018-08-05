package org.liamjd.web.services

import org.liamjd.web.db.dao.PageDAO
import org.liamjd.web.db.entities.PageTemplates
import org.liamjd.web.db.entities.Pages
import org.liamjd.web.model.Page

class DBPageService : PageService {

	val pageDAO = PageDAO()

	override fun getPage(refName: String): Page? {
		val found = pageDAO.getPage(refName) ?: return null
		val pageTemplate = pageDAO.getTemplate(found)
		return toModel(found, pageTemplate)

	}



	override fun save(page: Page): Boolean {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun countPages(): Int {
		return pageDAO.countPages()
	}

	private fun toModel(found: Pages, pageTemplate: PageTemplates): Page? {
		val page = Page(found.refName, found.uuid)
		page.title = found.title
		page.templateName = pageTemplate.refName
		return page
	}
}