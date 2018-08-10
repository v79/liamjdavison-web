package org.liamjd.web.controllers

import org.slf4j.LoggerFactory
import spark.Request
import spark.kotlin.after
import spark.kotlin.before
import spark.kotlin.notFound
import spark.template.thymeleaf.ThymeleafTemplateEngine

/**
 * Turn a simple map of strings into a JSON format string
 */
fun MutableMap<String, String>.toJson(): String {
	var sb = StringBuilder()
	sb.append("{")
	sb.append("\"errors\": [")
	val size = this.keys.size
	var count = 0
	this.forEach {
		count++
		sb.append("{")
		sb.append("\"field\": \"${it.key}\", ")
		sb.append("\"message\": \"${it.value}\"")
		sb.append("}")

		if (count != size) {
			sb.append(",")
		}
	}
	sb.append("]}")

	return sb.toString()
}


abstract class AbstractController(path: String) {
	open val logger = LoggerFactory.getLogger(AbstractController::class.java)

	open lateinit var path: String

	protected val engine: ThymeleafTemplateEngine = ThymeleafTemplateEngine()

	val model: MutableMap<String, Any> = hashMapOf<String, Any>()

	init {
		this.path = path

		before {
			logger.info("Request for " + request.pathInfo())
		}

		after {

		}

		notFound { "404 not found?"}


	}

	fun debugParams(request: Request) {
		request.params().forEach {
			logger.debug("Param ${it.key} -> ${it.value}")
		}
	}
}