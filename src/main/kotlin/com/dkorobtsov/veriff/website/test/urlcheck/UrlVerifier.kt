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

    fun getRedirectUrl(url: String): String? {
        val huc = URL(url).openConnection() as HttpURLConnection
        huc.instanceFollowRedirects = false
        huc.connect();
        val responseCode = when (huc.responseCode) {
            in 300..399 -> "unknown location"
            else -> "request code (${huc.responseCode}) is not a redirect"
        }
        return huc.getHeaderField("Location") ?: "Unknown"
    }

}
