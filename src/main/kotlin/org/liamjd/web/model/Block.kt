package org.liamjd.web.model

import org.liamjd.web.model.ref.BlockType
import java.util.*

data class Block(val refName: String, val uuid: UUID, val type: BlockType, var content: String) {

	fun render(): String {
		return type.renderer.render(content)
	}
}