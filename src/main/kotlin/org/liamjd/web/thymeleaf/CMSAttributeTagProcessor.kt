package org.liamjd.web.thymeleaf

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.engine.AttributeName
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.templatemode.TemplateMode
import org.unbescape.html.HtmlEscape

class CMSAttributeTagProcessor : AbstractAttributeTagProcessor {

	val body = """

	""".trimIndent()

	constructor(
			dialectPrefix: String?
	) : super(TemplateMode.HTML,		// This processor will apply only to HTML mode
			dialectPrefix, 				// Prefix to be applied to name for matching
			null, 			// No tag name: match any tag name
			false,		// No prefix to be applied to tag name
			"group", 		// Name of the attribute that will be matched
			true, 		// Apply dialect prefix to attribute name
			10000, 			// Precedence (inside dialect's precedence)
			true)			// Remove the matched attribute afterwards


	override fun doProcess(context: ITemplateContext?,
						   tag: IProcessableElementTag?,
						   attributeName: AttributeName?,
						   attributeValue: String?,
						   structureHandler: IElementTagStructureHandler?) {
		val finalString = """
						<div th:insert="~{fragments/blockgroup :: blockgroup(group=\$\{page.getGroup('$attributeValue')})}">page body block</div>

				"""
		structureHandler?.setBody(
				finalString, true);
	}
}