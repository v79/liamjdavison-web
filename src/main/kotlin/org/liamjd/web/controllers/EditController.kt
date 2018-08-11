package org.liamjd.web.controllers

import org.liamjd.web.annotations.AsJSON
import org.liamjd.web.annotations.SparkController
import org.liamjd.web.services.DBPageService
import spark.kotlin.get

@SparkController
class EditController : AbstractController("edit") {

	val pageService = DBPageService()

	init {
		@AsJSON
		get(path + "/getPageTemplates") {
			val pageTemplates = pageService.getPageTemplates()

			model.put("pageTemplates",pageTemplates)

			val sb = StringBuilder()
			sb.append("{")
			val tCount = pageTemplates.size
			var i = 0;
			for(t in pageTemplates) {
				i++
				sb.append("\"$t\"")
				if(i != tCount) {
					sb.append(",")
				}
			}
	 sb.toString()
		}
	}
}