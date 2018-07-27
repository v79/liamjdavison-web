package org.liamjd.web.render

class HtmlChannel(override val name: String = "HTML5") : Channel {
	override val fileSuffix: String?
		get() = "html"

}