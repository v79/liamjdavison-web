package org.liamjd.web.render.html

import org.liamjd.web.model.Block
import org.liamjd.web.render.HtmlResult
import org.liamjd.web.render.Renderer

class MarkdownRenderer : Renderer {
	override fun render(block: Block): HtmlResult {
		return "This is a markdown block -> ${block.content}"
	}
}