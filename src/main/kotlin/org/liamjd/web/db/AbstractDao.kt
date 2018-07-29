package org.liamjd.web.db

import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory

object dbConnections {
	val connectionString = "jdbc:mysql://127.0.0.1:3306/"
	val driverClass = "org.mariadb.jdbc.Driver"
	val dbDatabase = "liamjdavison"
	val dbPassword = "indy25tlx"
	val dbUser = "liam"
}

abstract class AbstractDao : Dao {
	open val daoLogger = LoggerFactory.getLogger(AbstractDao::class.java)

	init {
		val dbConnectionString: String = dbConnections.connectionString
		val dbName: String = dbConnections.dbDatabase
		val dbUser: String = dbConnections.dbUser
		val dbPassword: String = dbConnections.dbPassword
		val dbDriver: String = dbConnections.driverClass

		daoLogger.info("Connecting to DB: ${dbConnectionString}${dbName}")  // ?user=${dbUser}&password=${dbPassword}
		daoLogger.info("Using driver: ${dbDriver}")
		Database.connect(url = dbConnectionString + dbName, user = dbUser, password = dbPassword, driver = dbDriver)
	}
}