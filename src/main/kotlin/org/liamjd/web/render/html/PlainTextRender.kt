package org.liamjd.web.render.html

import javax.swing.Renderer

class PlainTextRender : org.liamjd.web.render.Renderer {
	override fun render(content: String): String {
		return "<p>$content</p>"
	}
}