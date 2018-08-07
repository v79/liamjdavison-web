package org.liamjd.web.services

import org.jetbrains.exposed.sql.transactions.transaction
import org.liamjd.web.db.dao.PageDAO
import org.liamjd.web.db.entities.BlockTypes
import org.liamjd.web.db.entities.Blocks
import org.liamjd.web.db.entities.PageTemplates
import org.liamjd.web.db.entities.Pages
import org.liamjd.web.model.Block
import org.liamjd.web.model.Page
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
			val block = Block(b.refName, b.uuid, getBlockType(b),b.content)

			modelBlocks.add(block)
		}

		return modelBlocks
	}


	private fun getBlockType(blocks: Blocks): BlockType {
		val type = pageDAO.getBlockType(blocks)
		return BlockType(type.refName,type.description)
	}
}