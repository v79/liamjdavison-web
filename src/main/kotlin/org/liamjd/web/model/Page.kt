package org.liamjd.web.model

import java.util.*

class Page (val refName: String, val uuid: UUID) {
	var title: String = ""
	var templateName = "home"
	var blocks = mutableListOf<Block>()
	var blockGroups = mutableListOf<BlockGroup>()

	override fun toString(): String {
		return "Page '$refName' [title=$title, uuid=$uuid, block count=${blocks.size}, block group count=${blockGroups.size}"
	}

	fun getGroup(name: String): BlockGroup?  {
		return blockGroups.find { it.refName == name }
	}
}
