package org.liamjd.web.controllers

import org.slf4j.LoggerFactory
import spark.kotlin.after
import spark.kotlin.before
import spark.kotlin.notFound
import spark.template.thymeleaf.ThymeleafTemplateEngine

abstract class AbstractController(path: String) {
	open val logger = LoggerFactory.getLogger(AbstractController::class.java)

	open lateinit var path: String

	protected val engine: ThymeleafTemplateEngine = ThymeleafTemplateEngine()

	val model: MutableMap<String,Any> = hashMapOf()

	init {
		this.path = path

		before {

		}

		after {

		}

		notFound { "404 not found?"}
	}
}