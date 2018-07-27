package org.liamjd.web.services

import org.liamjd.web.model.Block
import org.liamjd.web.model.BlockGroup
import org.liamjd.web.model.Page

class PageService {

	val pages = mutableListOf<Page>()

	val blocks = mutableListOf<Block>()
	val blockGroups = mutableListOf<BlockGroup>()


	val homePage = Page("home")

	val aboutPage = Page("about")

	init {

		for (i in 1..10) {
			val blk: Block = Block("$i")
			blk.source = "this is block $i"
			blocks.add(blk)
		}
		val groupA = BlockGroup("groupA")
		val groupB = BlockGroup("groupB")
		val groupC = BlockGroup("groupC")
		blockGroups.add(groupA)
		blockGroups.add(groupB)
		blockGroups.add(groupC)

		homePage.title = "Liam Davison"
		homePage.templateName = "home"
		homePage.blocks.add(blocks[0])
		homePage.blockGroups.addAll(blockGroups)
		groupA.blocks.addAll(blocks.filter { it.refName.toInt() < 3 })
		groupB.blocks.addAll(blocks.filter { it.refName.toInt() > 3 && it.refName.toInt() < 8 })
		groupC.blocks.addAll(blocks.filter { it.refName.toInt() > 7 })



		aboutPage.title = "About Me"
		aboutPage.templateName = "home"

		pages.add(homePage)
		pages.add(aboutPage)
	}

	fun getPage(refName: String): Page? {
		return pages.find { it.refName == refName }
	}
}