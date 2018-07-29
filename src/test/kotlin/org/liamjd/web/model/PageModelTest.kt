package org.liamjd.web.model

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.liamjd.web.render.HtmlChannel
import kotlin.test.assertEquals

/*
class PageModelTest: Spek({
	val page = Page("TestPage")
	val blockGroup = BlockGroup("firstBlock")
	val block = Block("helloBlock","plaintext")
	blockGroup.addBlock(block)
	page.addBlockgroup(blockGroup)
	page.defaultChannel = HtmlChannel()

	describe("Some basic setup of page, block, blockgroup and channel") {
		it("is HTML channel") {
			assertEquals("html", (page.defaultChannel as HtmlChannel).fileSuffix)
		}
		it("has one block group") {
			assertEquals(1,page.groups.size)
			assertEquals("firstBlock",page.groups.first().groupName)
		}
		it("has one block in the group") {
			val bG = page.groups.first()
			assertEquals(1,bG.blocks.size)
			assertEquals("helloBlock",bG.blocks.first().blockName)
			assertEquals("plaintext",bG.blocks.first().type)
		}
	}
})*/
