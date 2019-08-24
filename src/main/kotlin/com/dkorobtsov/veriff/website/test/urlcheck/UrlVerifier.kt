package com.dkorobtsov.veriff.website.test.urlcheck

import org.apache.logging.log4j.LogManager
import java.net.HttpURLConnection
import java.net.URL


class UrlVerifier {

    private val logger = LogManager.getLogger("StatusLogger")

    /**
     * Helper method to check if provided url is not broken
     * @return response status
     */
    fun checkUrl(url: String): Int {
        logger.debug("Checking url  : $url")
        val huc = URL(url).openConnection() as HttpURLConnection
        huc.requestMethod = "HEAD"
        logger.debug("Response code : ${huc.responseCode}")
        return huc.responseCode
    }

    /**
     * Helper method to identify redirect location
     */
    fun getRedirectUrl(url: String): String? {
        val huc = URL(url).openConnection() as HttpURLConnection
        huc.instanceFollowRedirects = false
        huc.connect();
        return huc.getHeaderField("Location")
            ?: when (huc.responseCode) {
                in 300..399 -> "Unknown Location"
                else -> "request code (${huc.responseCode}) is not a redirect"
            }
    }
}
