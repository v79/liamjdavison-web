package org.liamjd.web.controllers

import org.liamjd.caisson.extensions.bind
import org.liamjd.web.annotations.SparkController
import org.liamjd.web.model.security.AuthUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spark.kotlin.get
import spark.kotlin.post

@SparkController
class SecurityController : AbstractController("/auth") {

	override val logger: Logger = LoggerFactory.getLogger(this.javaClass)

		init {
			get(path) {
				"login please"
			}

			post(path + "/validateLogin") {
				val userDetails = request.bind<AuthUser>()

				logger.warn("Login attempted with $userDetails")

				"boo"
			}
		}
}