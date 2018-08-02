package org.liamjd.web.services

import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

object BlockX : LongIdTable() {
//	val id: Column<Long> = long("id").autoIncrement().primaryKey()
	val refName: Column<String> = varchar("refName",255).uniqueIndex()
	val uuid: Column<UUID> = uuid("uuid").clientDefault { UUID.randomUUID() }
}





class DSLDatabasePageServiceTest : Spek({

	val service = FakeDSLService()

	describe("Just a block") {
		it("Create a simple block") {
			transaction {
				val b = BlockX.insertAndGetId {
					it[refName] = "simpleBlock"
				}.value
				val resultRow = BlockX.select { BlockX.id eq b }.first()

				assertEquals("simpleBlock",resultRow[BlockX.refName])
			}
		}
	}




})



class FakeDSLService {
	init {

		val dbConnectionString: String = dsldbConnections.connectionString
		val dbName: String = dsldbConnections.dbDatabase
		val dbUser: String = dsldbConnections.dbUser
		val dbPassword: String = dsldbConnections.dbPassword
		val dbDriver: String = dsldbConnections.driverClass

		println("Connecting to DB: ${dbConnectionString}${dbName}")  // ?user=${dbUser}&password=${dbPassword}
		println("Using driver: ${dbDriver}")
		Database.connect(url = dbConnectionString + dbName, user = dbUser, password = dbPassword, driver = dbDriver)

		transaction {
			SchemaUtils.createMissingTablesAndColumns(BlockX)
			addLogger(StdOutSqlLogger)
		}
	}
}

object dsldbConnections {
	val connectionString = "jdbc:mysql://127.0.0.1:3306/"
	val driverClass = "org.mariadb.jdbc.Driver"
	val dbDatabase = "liamjdavison"
	val dbPassword = "indy25tlx"
	val dbUser = "liam"
}
