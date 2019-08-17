package com.dkorobtsov.veriff.website.test.urlcheck

import com.dkorobtsov.veriff.website.test.config.SuiteConfig
import org.aeonbits.owner.ConfigFactory
import org.apache.logging.log4j.LogManager.getLogger
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.nio.file.Paths

class UrlCollector {

    private val config = ConfigFactory.create(SuiteConfig::class.java)
    private val logger = getLogger("StatusLogger")

    fun collectUrls(): Set<Link> {
        val document = getDocument(config.baseUrl())

        val mainPageLinks = extractLinks(document)
            .distinctBy { (url, _) -> url }
            .toSet()

        val siteSections = mainPageLinks
            .filter { (url, name) ->
                name.isNotEmpty() && name != MAIN_PAGE_NAME // to avoid double checking main page
                        && url.startsWith(config.baseUrl()) // to avoid collecting links from external websites
            }

        val allLinks = mutableSetOf<Link>()
        allLinks.addAll(mainPageLinks)
        siteSections.parallelStream().forEach {
            val section = getDocument(it.url)
            val sectionLinks = extractLinks(section)
            allLinks.addAll(sectionLinks)
        }

        val namedLinks = allLinks
            .distinctBy { (url, _) -> url }
            .map(this@UrlCollector::addMissingName)
            .toSet()

        if (namedLinks.isNotEmpty()) {
            logger.debug("\nSuccessfully collected URLs:")
            namedLinks.forEach { logger.debug(it) }
        }

        return namedLinks
    }

    private fun getDocument(url: String): Document {
        return Jsoup.connect(url)
            // here we don't care about certificates, let's accept them all
            .sslSocketFactory(SSLSocketFactoryProvider().allTrustingSSLSocketFactory())
            // otherwise JSoup will parse only part of document body
            .maxBodySize(0)
            .get()
    }

    private fun extractLinks(document: Document): List<Link> {
        return document.select(URL_ELEMENT)
            .mapNotNull { entry -> extractLink(entry) }
            .filter { (url, _) -> url.isNotEmpty() }
            .filter { (url, _) -> url.startsWith(VALID_URL_PREFIX) }
            .toList()
    }

    private fun extractLink(element: Element): Link {
        val linkName = element.text()
            ?: element.select(ENDPOINT_NAME_ELEMENT).text()

        val endpointUrl = element.select(URL_ELEMENT)
            .first()
            .attr(ABSOLUTE_URL_ATTRIBUTE)

        return Link(endpointUrl, linkName)
    }

    private fun extractNameFromUrl(url: String): String {
        val urlPath = Paths.get(url)
        return urlPath.getName(urlPath.nameCount - 1).toString()
    }

    private fun addMissingName(siteLink: Link): Link {
        return when {
            siteLink.name.isEmpty() -> Link(
                siteLink.url,
                extractNameFromUrl(siteLink.url)
            )
            else -> siteLink
        }
    }

    data class Link(val url: String, val name: String)

    companion object {
        private const val MAIN_PAGE_NAME = "Veriff"
        private const val VALID_URL_PREFIX = "http"

        private const val URL_ELEMENT = "a"
        private const val ENDPOINT_NAME_ELEMENT = "span"
        private const val ABSOLUTE_URL_ATTRIBUTE = "abs:href"
    }

}
