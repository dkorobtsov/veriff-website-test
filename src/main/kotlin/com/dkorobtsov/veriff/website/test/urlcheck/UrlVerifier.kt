package com.dkorobtsov.veriff.website.test.urlcheck

import org.apache.logging.log4j.LogManager
import java.net.HttpURLConnection
import java.net.URL


class UrlVerifier {

    private val logger = LogManager.getLogger("StatusLogger")

    fun checkUrl(url: String): Int {
        logger.debug("Checking url  : $url")
        val huc = URL(url).openConnection() as HttpURLConnection
        huc.requestMethod = "HEAD"
        logger.debug("Response code : ${huc.responseCode}")
        return huc.responseCode
    }

}