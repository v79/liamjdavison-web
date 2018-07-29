package org.liamjd.web.services

import org.liamjd.web.db.entities.PageEntityDao
import org.liamjd.web.model.Page

class DBPageService : PageService {

	val pageDao: PageEntityDao = PageEntityDao()

	override fun getPage(refName: String): Page? {
		return pageDao.getPage(refName)
	}

	override fun save(page: Page): Boolean {
		val result = pageDao.savePage(page)
		if (result > 0L) return true
		return false
	}

	override fun countPages(): Int {
		val pageCount = pageDao.countPages()
		println("$pageCount pages found")
		return pageCount
	}
}