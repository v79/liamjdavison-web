package org.liamjd.web.services

import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertTrue

object BlockX : Table() {
	val id: Column<Long> = long("id").autoIncrement().primaryKey()
	val refName: Column<String> = varchar("refName",255).uniqueIndex()
	val uuid: Column<UUID> = uuid("uuid").clientDefault { UUID.randomUUID() }
}

fun BlockX.get(resultRow: ResultRow): BlockC {
	return BlockC(resultRow[BlockX.id],resultRow[BlockX.refName],resultRow[BlockX.uuid])
}

//fun <T> Table.get(resultRow: ResultRow, clazz: KClass<T>) : T {
//	clazz.
//}

data class BlockC(val id: Long, val refName: String, val uuid: UUID) {
	constructor(x: ResultRow) : this(x[BlockX.id],x[BlockX.refName],x[BlockX.uuid])
}




class DSLDatabasePageServiceTest : Spek({

	val service = FakeDSLService()

	describe("Just a block") {
		it("Create a simple block") {
			transaction {
				val b = BlockX.insert {
					it[refName] = "simpleBlock"
				} get BlockX.id
				val resultRow = BlockX.select { BlockX.id eq b!! }.first()

				assertEquals("simpleBlock",BlockX.get(resultRow).refName)
				assertEquals("simpleBlock",BlockC(resultRow).refName)
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
