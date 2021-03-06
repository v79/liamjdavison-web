package org.liamjd.web.controllers

import org.liamjd.caisson.extensions.bind
import org.liamjd.web.annotations.AsJSON
import org.liamjd.web.annotations.SparkController
import org.liamjd.web.services.DBPageService
import spark.ModelAndView
import spark.kotlin.before
import spark.kotlin.get
import spark.kotlin.post

data class NewPageForm(val refName: String, val title: String, val pageTemplateName: String)
@SparkController
class EditController : AbstractController("edit") {

	val pageService = DBPageService()

	init {

		before("$path/*") {
			println("authenticate all edit requests?")
			if(session().attribute<String>("user").isNullOrBlank()) {
				logger.error("Unauthorised request to ${request.pathInfo()} made. Redirecting to home")
				redirect("/")
			}
		}

		@AsJSON
		get("$path/getPageTemplates") {
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

		@AsJSON
		post("$path/validateCreatePage") {
			val errorMap = ErrorMap()
			val form = request.bind<NewPageForm>()
			if(form?.refName?.isNotBlank()!!) {
				// TODO validate reference name. For now assume all strings are allowed
				val isUnique = pageService.isUniqueRef(form.refName)
				if(!isUnique) {
					errorMap.put("refName","That reference name is already taken. All references must be unique.")
				}
			} else {
				errorMap.put("refName","Reference name cannot be blank")
			}
			if(form.pageTemplateName.isNullOrBlank()) {
				errorMap.put("pageTemplateName","You must choose a page template")
			}

			if(errorMap.isNotEmpty()) {
				model.put("errors",errorMap)
				response.type("application/json")
				errorMap.toJson()
			} else {
				// now, create our page
				val page = pageService.createPage(form.refName, form.title, form.pageTemplateName)

				redirect("${path}/${page.refName}")
			}
		}

		get("$path/:pageName") {

			model.put("__mode",Mode.EDIT)
			val page = pageService.getPage(request.params("pageName"))

			if(page != null) {
				model.put("page", page)
				engine.render(ModelAndView(model, page.templateName))
			} else {
				"Page ${request.params("pageName")} not found"
			}
		}
	}
}