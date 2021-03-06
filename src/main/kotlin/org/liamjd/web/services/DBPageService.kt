package org.liamjd.web.services

import org.liamjd.web.db.dao.PageDAO
import org.liamjd.web.db.entities.BlockTypes
import org.liamjd.web.db.entities.Blocks
import org.liamjd.web.db.entities.PageTemplates
import org.liamjd.web.db.entities.Pages
import org.liamjd.web.model.Block
import org.liamjd.web.model.Page
import org.liamjd.web.model.PageTemplate
import org.liamjd.web.model.ref.BlockType

class DBPageService : PageService {

	val pageDAO = PageDAO()

	override fun getPage(refName: String): Page? {
		val found = pageDAO.getPage(refName) ?: return null
		val pageTemplate = pageDAO.getTemplate(found)
		val pageBlocks = pageDAO.getBlocks(found)
		return toModel(found, pageTemplate, pageBlocks)

	}



	override fun save(page: Page): Boolean {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun countPages(): Int {
		return pageDAO.countPages()
	}

	override fun getPageTemplates(): List<PageTemplate> {
		val pageTemplates = mutableListOf<PageTemplate>()
		val result = pageDAO.getAllTemplates()
		for(t in result) {
			pageTemplates.add(PageTemplate(t.refName))
		}
		return pageTemplates
	}

	override fun isUniqueRef(refName: String): Boolean {
		val pages = pageDAO.getPage(refName)
		if(pages != null) {
			return false
		}
		return true
	}

	override fun createPage(refName: String, title: String, pageTemplateName: String): Page {
		val pages = pageDAO.createPage(refName, title, pageTemplateName)

		return getPage(pages.refName)!!
	}

	private fun toModel(found: Pages, pageTemplate: PageTemplates, blocks: Set<Blocks>): Page? {
		val page = Page(found.refName, found.uuid)
		page.title = found.title
		page.templateName = pageTemplate.refName
		page.blocks = toModel(blocks)
		return page
	}

	private fun toModel(found: Set<Blocks>): MutableList<Block> {
		val modelBlocks = mutableListOf<Block>()
		for(b in found) {
			val blockTypes = pageDAO.getBlockType(b)
			val type = toBlockTypeModel(blockTypes!!)
			val block = Block(b.refName, b.uuid, type ,b.content)

			modelBlocks.add(block)
		}

		return modelBlocks
	}

	private fun toBlockTypeModel(type: BlockTypes) : BlockType {
		return BlockType(type.refName, type.description)
	}

}