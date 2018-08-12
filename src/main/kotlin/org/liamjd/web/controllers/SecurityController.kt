package org.liamjd.web.controllers

import org.liamjd.caisson.extensions.bind
import org.liamjd.web.annotations.AsJSON
import org.liamjd.web.annotations.SparkController
import org.liamjd.web.model.security.AuthUser
import org.liamjd.web.services.auth.FakeAuthService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spark.kotlin.get
import spark.kotlin.post

@SparkController
class SecurityController : AbstractController("/auth") {

	override val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	// hack for now
	override val authService = FakeAuthService()

	init {
		get(path) {
			"login please"
		}


		@AsJSON
		post(path + "/validateLogin") {
			debugParams(request)
			val userDetails = request.bind<AuthUser>()
			val errorMap = ErrorMap()
			if (userDetails?.password?.length!! < 6) {
				errorMap.put("password", "At least 6 characters long")
			}
			if (userDetails.username.isBlank()) {
				errorMap.put("username", "Username cannot be blank")
			}
			if (errorMap.isNotEmpty()) {
				model.put("errors", errorMap)
				response.type("application/json")
				logger.info("returning error map")
				logger.info(errorMap.toJson())
				errorMap.toJson()
			} else {
				logger.warn("Login attempted with $userDetails")
					val authenticated = authService.authenticateUser(userDetails)
				if(authenticated != null) {
					request.session(true)
					request.session().attribute("user",userDetails.username)
					request.session().attribute("token",userDetails.token)
					"authenticated ${session()}"
				}
			"go away bad person"
			}
		}

		get(path + "/loggedIn") {
			"${session()} logged in!"
		}

	}
}