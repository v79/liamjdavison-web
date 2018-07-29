package org.liamjd.web.services

import org.liamjd.web.model.Block
import org.liamjd.web.model.BlockGroup
import org.liamjd.web.model.Page

class InMemoryPageService : PageService {
	override fun save(page: Page): Boolean {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	val pages = mutableListOf<Page>()

	val blocks = mutableListOf<Block>()
	val blockGroups = mutableListOf<BlockGroup>()


	val homePage = Page("home")

	val aboutPage = Page("about")

	init {

		for (i in 1..10) {
			val blk: Block = Block("$i")
			blk.source = """
				$i
				When I started <em>Caisson</em>, I was thinking about trying to bring the functionality from SpringMVC into spark-kotlin, especially around validation of form elements. But now that <em>Caisson</em> is working, I've backed off a little from that. spark-kotlin is deliberately lightweight and overloading Caisson with too many extra features would complicate things unneccessarily. Nonetheless, I'm still thinking about a validation framework based around Caisson (perhaps as a separate module?). Caisson also contains an <code>AbstractController</code> class which I'm using in <em>spark-caisson-integration</em> which adds additional logging, debugging and flash-scope mechanisms. I think that's the wrong approach, so I'm going to unpick it and narrow the focus. Kotlin's extension functions can help with this - I can imagine implementing a flash session scope with a simple <code>request.flash("object","value")</code> extension method.
			""".trimIndent()
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

	override fun getPage(refName: String): Page? {
		return pages.find { it.refName == refName }
	}

	override fun countPages(): Int {
		return pages.size
	}
}