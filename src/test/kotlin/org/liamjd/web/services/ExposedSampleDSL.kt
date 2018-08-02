package org.liamjd.web.services

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.liamjd.web.services.dsldbConnections.dbPassword
import org.liamjd.web.services.dsldbConnections.dbUser

object UserX : Table() {
	val id = varchar("id", 10).primaryKey() // Column<String>
	val name = varchar("name", length = 50) // Column<String>
	val cityId = (integer("city_id") references Cities.id).nullable() // Column<Int?>
}

object Cities : Table() {
	val id = integer("id").autoIncrement().primaryKey() // Column<Int>
	val name = varchar("name", 50) // Column<String>
}

fun Cities.fromRow(resultRow: ResultRow): MyCity {
	return MyCity(resultRow[Cities.id], resultRow[Cities.name])
}

data class MyCity(val id: Int, val name: String) {

}

fun main(args: Array<String>) {
	val dbConnectionString: String = dsldbConnections.connectionString

	Database.connect(url = dbConnectionString + dsldbConnections.dbDatabase, user = dbUser, password = dbPassword, driver = dsldbConnections.driverClass)


	transaction {
		drop (UserX, Cities)
		create (Cities, UserX)

		val saintPetersburgId = Cities.insert {
			it[name] = "St. Petersburg"
		} get Cities.id

		val munichId = Cities.insert {
			it[name] = "Munich"
		} get Cities.id

		Cities.insert {
			it[name] = "Prague"
		}

		UserX.insert {
			it[id] = "andrey"
			it[name] = "Andrey"
			it[cityId] = saintPetersburgId
		}

		UserX.insert {
			it[id] = "sergey"
			it[name] = "Sergey"
			it[cityId] = munichId
		}

		UserX.insert {
			it[id] = "eugene"
			it[name] = "Eugene"
			it[cityId] = munichId
		}

		UserX.insert {
			it[id] = "alex"
			it[name] = "Alex"
			it[cityId] = null
		}

		UserX.insert {
			it[id] = "smth"
			it[name] = "Something"
			it[cityId] = null
		}

		UserX.update({UserX.id eq "alex"}) {
			it[name] = "Alexey"
		}

		UserX.deleteWhere{UserX.name like "%thing"}

		println("All cities:")

		for (city in Cities.selectAll()) {
			println("${city[Cities.id]}: ${city[Cities.name]}")
		}

		println("just Munich:")

		Cities.select { Cities.name eq("Munich") }.first().also {
			println("${it[Cities.id]}: ${it[Cities.name]}")
			println("Using extension function to create MyCity object")
			println(Cities.fromRow(it))
		}

		println("Manual join:")
		(UserX innerJoin Cities).slice(UserX.name, Cities.name).
				select {(UserX.id.eq("andrey") or UserX.name.eq("Sergey")) and
						UserX.id.eq("sergey") and UserX.cityId.eq(Cities.id)}.forEach {
			println("${it[UserX.name]} lives in ${it[Cities.name]}")
		}

		println("Join with foreign key:")


		(UserX innerJoin Cities).slice(UserX.name, UserX.cityId, Cities.name).
				select {Cities.name.eq("St. Petersburg") or UserX.cityId.isNull()}.forEach {
			if (it[UserX.cityId] != null) {
				println("${it[UserX.name]} lives in ${it[Cities.name]}")
			}
			else {
				println("${it[UserX.name]} lives nowhere")
			}
		}

		println("Functions and group by:")

		((Cities innerJoin UserX).slice(Cities.name, UserX.id.count()).selectAll().groupBy(Cities.name)).forEach {
			val cityName = it[Cities.name]
			val userCount = it[UserX.id.count()]

			if (userCount > 0) {
				println("$userCount user(s) live(s) in $cityName")
			} else {
				println("Nobody lives in $cityName")
			}
		}

//		drop (UserX, Cities)

	}
}