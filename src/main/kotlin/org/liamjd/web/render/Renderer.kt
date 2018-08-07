package org.liamjd.web.render

// For now, this will only render to one channel, HTML. Others may follow
interface Renderer {
	fun render(content: String): String
}