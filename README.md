# liamjdavison-web
Personal website for liamjdavison.co.uk - using a combination of Spark-Kotlin, Thymeleaf, Exposed



# Notes
A City has many Users
A User has one city

A Page has many blocks
A Block has one page



  object Blocks : IntIdTable() {
    val refName = varchar("refName",255).index()
    val source = text("source")

    val page = reference("page",Pages)
  }

  object Pages : IntIdTable() {
    val refName = varchar("refName",255).index()
    val title = text("title",1000).index()
  }

  class Block(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Block>(Blocks)

    var refName by Blocks.refName
    var source by Blocks.source

    var page by Page referencedOn Pages.city
  }

  class Page(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Page>(Pages)

    var refName by Pages.refName

    val blocks by Block referrersOn Blocks.page
  }
