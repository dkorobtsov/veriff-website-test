package com.dkorobtsov.veriff.website.test.url

import com.dkorobtsov.veriff.website.test.constants.EPIC_VERIFF_WEBSITE
import com.dkorobtsov.veriff.website.test.constants.FEATURE_HYPERLINKS_VALIDATION
import com.dkorobtsov.veriff.website.test.constants.STORY_STATUS_CODE_CHECK
import com.dkorobtsov.veriff.website.test.urlcheck.UrlCollector
import com.dkorobtsov.veriff.website.test.urlcheck.UrlVerifier
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.net.HttpURLConnection.*
import java.util.stream.Stream

@Epic(EPIC_VERIFF_WEBSITE)
@Feature(FEATURE_HYPERLINKS_VALIDATION)
@Story(STORY_STATUS_CODE_CHECK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlCheckerTest {

    @ParameterizedTest
    @MethodSource("siteUrls")
    fun `Verify that URL is not broken`(siteLink: UrlCollector.Link) {
        val actualStatus = UrlVerifier().checkUrl(siteLink.url)
        val expectedStatus = HTTP_OK

        skipTestForBlocksAndRedirects(actualStatus)
        Assertions.assertEquals(
            expectedStatus, actualStatus,
            "Expected ${siteLink.name} link [${siteLink.url}] " +
                    "\n to respond with: $expectedStatus" +
                    "\n but was: $actualStatus"
        )
    }

    @Suppress("unused")
    fun siteUrls(): Stream<UrlCollector.Link> {
        return UrlCollector().collectUrls().stream()
    }

    private fun skipTestForBlocksAndRedirects(status: Int) {
        // As long it's not 404 or 5xx response, we are OK with that (probably page is blocking bots)
        Assumptions.assumeFalse(status == HTTP_BAD_METHOD, "Method not allowed")
        Assumptions.assumeFalse(status == HTTP_FORBIDDEN, "Forbidden")
        Assumptions.assumeFalse(status == HTTP_MOVED_PERM, "Moved Permanently")
        Assumptions.assumeFalse(status == HTTP_MOVED_TEMP, "Temporary Redirect")

        //LinkedIn specific response status for Web Crawlers
        Assumptions.assumeFalse(status == 999, "Method not allowed")
    }

}
