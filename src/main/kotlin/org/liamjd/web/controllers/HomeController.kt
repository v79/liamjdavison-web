package org.liamjd.web.controllers

import org.liamjd.web.annotations.SparkController
import org.liamjd.web.services.DBPageService
import org.liamjd.web.services.PageService
import spark.ModelAndView
import spark.kotlin.get

@SparkController
class HomeController : AbstractController(path = "/") {

	val pageService: PageService


	init {
		// TODO this should happen via DI eventually
		pageService = DBPageService()

		get(path) {
			val homePage = pageService.getPage("home")

			pageService.countPages()

			if (homePage != null) {
				model.put("page", homePage)
				engine.render(ModelAndView(model, homePage.templateName))
			} else {
				"Homepage not found"
			}
		}

		get("pages/:pageName") {
			val page = pageService.getPage(request.params("pageName"))
			if(page != null) {
				model.put("page", page)
				engine.render(ModelAndView(model, page.templateName))
			} else {
				"Page ${request.params("pageName")} not found"
			}
		}


		/*	get("/edit/new/:postName") {
				val postName = request.params(":postName")

				val page: Page = Page(postName,"no body for ${postName} yet")
				pages.add(page)

				"created page ${postName}"
			}*/

	}
}






