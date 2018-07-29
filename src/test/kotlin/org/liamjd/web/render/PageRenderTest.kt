package org.liamjd.web.render

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.liamjd.web.model.Block
import org.liamjd.web.model.BlockGroup
import org.liamjd.web.model.Page
import kotlin.test.assertEquals

/*
class PageRenderTest: Spek({
	val page = Page("TestPage")
	val blockGroup = BlockGroup("firstBlock")
	val block = Block("helloBlock","plaintext")
	block.source = "Wednesday"
	blockGroup.addBlock(block)
	page.addBlockgroup(blockGroup)
	page.defaultChannel = HtmlChannel()

	val plainResult = """<html><head><title>TestPage</title></head><body><p>Wednesday</p></body></html>"""

	describe("Render the simplest plaintext block as HTML5") {
		it("turns plain text into simple HTML with default channel") {
			assertEquals(plainResult,page.render())
		}
	}
})*/
