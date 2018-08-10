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
			debugParams(request)
			val userDetails = request.bind<AuthUser>()
			val errorMap = mutableMapOf<String, String>()
			if (userDetails?.password?.length!! < 6) {
				errorMap.put("password", "At least 6 characters long")
			}
			if (userDetails.username.isBlank()) {
				errorMap.put("username", "Username cannot be blank")
			}
			model.put("errors", errorMap)
			if (errorMap.isNotEmpty()) {
				response.type("application/json")
				logger.info("returning error map")
				logger.info(errorMap.toJson())
				errorMap.toJson()

			} else {
				logger.warn("Login attempted with $userDetails")

				"boo"
			}
		}

		get(path + "/loggedIn") {
			"you logged in!"
		}

	}
}