package org.liamjd.web.services.auth

import org.liamjd.web.model.security.AuthUser

interface AuthenticationService {

	fun authenticateUser(authUser: AuthUser): String?

	fun getUser(userName: String) : AuthUser?
}


class FakeAuthService : AuthenticationService {

	val validUsers = mutableListOf(AuthUser("liam", "password"), AuthUser("neil", "dunfermline"))

	override fun authenticateUser(authUser: AuthUser): String? {
		for (user in validUsers) {
			if (authUser.username == user.username) {
				if (authUser.password == user.password) {
					return user.token
				}
			}
		}

		return null
	}

	override fun getUser(userName: String): AuthUser? {
		return validUsers.find { it.username == userName }
	}
}