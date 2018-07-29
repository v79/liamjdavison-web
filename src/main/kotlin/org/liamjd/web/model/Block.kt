package org.liamjd.web.model

import java.util.*

data class Block(val refName: String) {
	val uuid: UUID = UUID.randomUUID()
	var source: String = ""
	private var result: String = ""

	fun render(): String {
	/*	val split = source.split("\\w")
		val sb: StringBuilder = StringBuilder()
		sb.append("<span id=$refName>")
		for(word in split) {
			if(Math.random() < 0.5) {
				sb.append("<strong>$word</strong>")
			} else {
				sb.append("<em>$word</em>")
			}
			sb.append(" ")
		}
		sb.append("</span>")
		result = sb.toString()
		return result.toUpperCase()*/

		return source

	}
}