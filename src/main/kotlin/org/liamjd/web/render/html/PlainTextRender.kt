package org.liamjd.web.render.html

import org.liamjd.web.model.Block
import org.liamjd.web.render.HtmlResult

class PlainTextRender : org.liamjd.web.render.Renderer {
	override fun render(block: Block): HtmlResult {
		return "<p>${block.refName} : ${block.content}</p>"
	}
}