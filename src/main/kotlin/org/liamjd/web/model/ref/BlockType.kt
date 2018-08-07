package org.liamjd.web.model.ref

import org.liamjd.web.render.Renderer
import org.liamjd.web.render.html.PlainTextRender

data class BlockType(val refName: String, val description: String) {
	lateinit var renderer: Renderer

	init {
		when(refName) {
			"text" -> renderer = PlainTextRender()
			"html" -> renderer = PlainTextRender()
			"markdown" -> renderer = PlainTextRender()
		}

	}
}