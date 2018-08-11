package org.liamjd.web.model.security

import java.util.*

data class AuthUser(val username: String, val password: String) {
	val token: String

	init {
		token = UUID.randomUUID().toString()
	}
}