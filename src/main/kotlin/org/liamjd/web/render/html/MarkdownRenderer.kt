package org.liamjd.web.render.html

import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.liamjd.web.model.Block
import org.liamjd.web.render.HtmlResult
import org.liamjd.web.render.Renderer

class MarkdownRenderer : Renderer {
	override fun render(block: Block): HtmlResult {

		val markdownToHtmlRenderer = HtmlRenderer.builder()
				.build()
		val parser = Parser.builder().build()
		val document = parser.parse(block.content)
		val htmlResult = markdownToHtmlRenderer.render(document)
		return "This is a markdown block -> $htmlResult"
	}
}