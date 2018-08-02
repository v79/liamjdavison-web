package org.liamjd.web.services

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.liamjd.web.db.dbConnections

object StarWarsFilms : IntIdTable() {
	val sequelId = integer("sequel_id").uniqueIndex().nullable()
	val name = varchar("name", 50)
	val director = varchar("director", 50)
}

class StarWarsFilm(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<StarWarsFilm>(StarWarsFilms)

	var sequelId by StarWarsFilms.sequelId
	var name by StarWarsFilms.name
	var director by StarWarsFilms.director
}

object Users : IntIdTable() {
	val name = varchar("name", 50)
}

class User(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<User>(Users)

	var name by UserX.name
}

object UserRatings : IntIdTable() {
	val value = long("value")
	val film = reference("film", StarWarsFilms)
	val user = reference("user", Users)
}

class UserRating(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<UserRating>(UserRatings)

	var value by UserRatings.value
	var film by StarWarsFilm referencedOn UserRatings.film // use referencedOn for normal references
	var user by User referencedOn UserRatings.user
}


class StarWarsTest : Spek({
	val service = SWService()

	describe("Do things with star wars") {
		transaction {
			val liam = User.new {
				name = "Liam"
			}
			val phantom = StarWarsFilm.new {
				name = "Phantom Menace"
				director = "I don't know"
			}
			val liamHatesPhantom = UserRating.new {
				film = phantom
				user = liam
				value = 1

			}
		}
	}
})


class SWService {
	init {

		val dbConnectionString: String = dbConnections.connectionString
		val dbName: String = dbConnections.dbDatabase
		val dbUser: String = dbConnections.dbUser
		val dbPassword: String = dbConnections.dbPassword
		val dbDriver: String = dbConnections.driverClass

		println("Connecting to DB: ${dbConnectionString}${dbName}")  // ?user=${dbUser}&password=${dbPassword}
		println("Using driver: ${dbDriver}")
		Database.connect(url = dbConnectionString + dbName, user = dbUser, password = dbPassword, driver = dbDriver)

		transaction {
			SchemaUtils.createMissingTablesAndColumns(StarWarsFilms)
			SchemaUtils.createMissingTablesAndColumns(UserX)
			SchemaUtils.createMissingTablesAndColumns(UserRatings)
			addLogger(StdOutSqlLogger)
		}
	}
}

object dbConnections {
	val connectionString = "jdbc:mysql://127.0.0.1:3306/"
	val driverClass = "org.mariadb.jdbc.Driver"
	val dbDatabase = "liamjdavison"
	val dbPassword = "indy25tlx"
	val dbUser = "liam"
}

/*
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
}*/
