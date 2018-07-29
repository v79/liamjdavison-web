package org.liamjd.web.services

import org.liamjd.web.model.Page

interface PageService {
	fun getPage(refName: String): Page?

	fun save(page: Page) : Boolean

	fun countPages() : Int
}