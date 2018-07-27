package org.liamjd.web.thymeleaf

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template
import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.model.IModel
import org.thymeleaf.processor.element.AbstractElementModelProcessor
import org.thymeleaf.processor.element.IElementModelStructureHandler
import org.thymeleaf.templatemode.TemplateMode

class CMSCustomElementProcessor : AbstractElementModelProcessor {
	constructor(dialectPrefix: String) : super(TemplateMode.HTML, dialectPrefix, "g", true, null, false, 10000)

	override fun doProcess(context: ITemplateContext?, model: IModel?, structureHandler: IElementModelStructureHandler?) {

	}
}