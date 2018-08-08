package org.liamjd.web.render

import org.liamjd.web.model.Block

typealias HtmlResult = String;
// For now, this will only render to one channel, HTML. Others may follow
interface Renderer {
	fun render(block: Block): HtmlResult
}