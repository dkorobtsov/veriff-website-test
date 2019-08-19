package com.dkorobtsov.veriff.website.test.url

import com.dkorobtsov.veriff.website.test.constants.EPIC_VERIFF_WEBSITE
import com.dkorobtsov.veriff.website.test.constants.FEATURE_HYPERLINKS_VALIDATION
import com.dkorobtsov.veriff.website.test.constants.STORY_REDIRECT_CHECK
import com.dkorobtsov.veriff.website.test.urlcheck.UrlVerifier
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.net.HttpURLConnection.HTTP_MOVED_PERM
import java.util.stream.Stream


@Epic(EPIC_VERIFF_WEBSITE)
@Feature(FEATURE_HYPERLINKS_VALIDATION)
@Story(STORY_REDIRECT_CHECK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpsRedirectTest {

    @ParameterizedTest
    @MethodSource("expectedRedirects")
    fun `User automatically redirected from http to https page`(
        statusCode: Int,
        requestUrl: String,
        redirectUrl: String
    ) {
        assertRedirect(statusCode, requestUrl, redirectUrl)
    }

    @Suppress("unused")
    private fun expectedRedirects(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(
                PERMANENT_REDIRECT,
                "http://veriff.com",
                "https://veriff.com/"
            ),
            Arguments.of(
                PERMANENT_REDIRECT,
                "http://www.veriff.com",
                "https://www.veriff.com/"
            ),
            Arguments.of(
                HTTP_MOVED_PERM,
                "http://veriff-support.helpscoutdocs.com",
                "https://veriff-support.helpscoutdocs.com/"
            ),
            Arguments.of(
                PERMANENT_REDIRECT,
                "http://office.veriff.me/login",
                "https://office.veriff.me/login"
            )
        )
    }

    private fun assertRedirect(expectedResponseCode: Int, requestUrl: String, redirectUrl: String) {
        val actialResponseCode = UrlVerifier().checkUrl(requestUrl)
        Assertions.assertEquals(
            expectedResponseCode, actialResponseCode,
            "\nExpecting [$requestUrl] " +
                    "\nto respond with: $expectedResponseCode" +
                    "\nbut was: $actialResponseCode"
        )

        val location = UrlVerifier().getRedirectUrl(requestUrl)
        Assertions.assertEquals(
            redirectUrl, location,
            "\nExpecting all requests to [$requestUrl] " +
                    "\nto be redirected to [$redirectUrl]" +
                    "\nbut redirect was set to [$location]"
        )
    }

    companion object {
        private const val PERMANENT_REDIRECT = 308
    }

}
