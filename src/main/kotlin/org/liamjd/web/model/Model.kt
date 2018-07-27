/*
package org.liamjd.web.model

import org.liamjd.web.render.Channel

data class Page(val name: String) {


	val groups = mutableSetOf<BlockGroup>()
	val channels = setOf<Channel>()
	var defaultChannel: Channel? = null
//	val renderers = setOf<Renderer>()
//	var defaultRenderer: Renderer? = null

	fun render(): String {
		if (defaultChannel != null) {
			return render(defaultChannel!!)
		} else {
			return ""
		}
	}

	fun render(channel: Channel): String {

		var result = StringBuilder()
		for (g in groups) {
			result.append(g.render(channel))
		}
		return result.toString()
	}

	fun addBlockgroup(blockGroup: BlockGroup) {
		groups.add(blockGroup)
	}

	fun removeBlockGroup(blockGroup: BlockGroup) {
		groups.remove(blockGroup)
	}

}

class BlockGroup(val groupName: String) {

	val blocks = mutableListOf<Block>()

	fun addBlock(block: Block) {
		blocks.add(block)
	}

	fun removeBlock(block: Block) {
		blocks.remove(block)
	}

	fun render(channel: Channel): String {
		var result = StringBuilder()
		for (b in blocks) {
			result.append(b.render(channel))
		}
		return result.toString()
	}

}

class Block(val blockName: String, val type: String) {

	var source: String = ""

	fun render(channel: Channel): String {
		return source
	}

}*/
