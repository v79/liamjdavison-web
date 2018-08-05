package org.liamjd.web.db.dao

import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.liamjd.web.db.AbstractDao
import org.liamjd.web.db.Dao
import org.liamjd.web.db.entities.*

class PageDAO : AbstractDao(), Dao {

	fun getPage(refName: String): Pages? =
			transaction {
				Pages.find { PAGE.refName eq refName }.firstOrNull()
			}

	fun getTemplate(found: Pages): PageTemplates =
			transaction {
				PageTemplates.get(found.template.id)
			}

	fun getBlockGroups(page: Pages): Set<BlockGroups> {
		val groupSet = mutableSetOf<BlockGroups>()
		transaction {
			val groups =
					BLOCK_GROUP.innerJoin(PAGE)
							.slice(BLOCK_GROUP.columns)
							.select { BLOCK_GROUP.page eq page.id }

			if (groups.count() != 0) {
				groups.forEach {
					groupSet.add(BlockGroups.wrapRow(it))
				}
			}
		}
		return groupSet
	}

	fun countPages(): Int = transaction { Pages.count() }
}