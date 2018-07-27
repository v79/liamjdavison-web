package org.liamjd.web.thymeleaf

import org.thymeleaf.processor.IProcessor
import java.util.HashSet
import org.thymeleaf.dialect.AbstractProcessorDialect

// Dialect name
// Dialect prefix (cms:*)
// Dialect precedence
class CMSDialect : AbstractProcessorDialect("CMS Dialect", "cms", 1000) {

	/*
     * Initialize the dialect's processors.
     *
     * Note the dialect prefix is passed here because, although we set
     * "cms" to be the dialect's prefix at the constructor, that only
     * works as a default, and at engine configuration time the user
     * might have chosen a different prefix to be used.
     */
	override fun getProcessors(dialectPrefix: String): Set<IProcessor> {
		val processors = HashSet<IProcessor>()
		processors.add(CMSAttributeTagProcessor(dialectPrefix))
		return processors
	}


}