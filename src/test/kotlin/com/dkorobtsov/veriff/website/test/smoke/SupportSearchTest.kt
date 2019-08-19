package com.dkorobtsov.veriff.website.test.smoke

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.junit5.TextReportExtension
import com.dkorobtsov.veriff.website.test.constants.*
import com.dkorobtsov.veriff.website.test.extension.DriverPerClassExtension
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@Epic(EPIC_VERIFF_WEBSITE)
@Feature(FEATURE_SUPPORT)
@Story(STORY_SEARCH)
@ExtendWith(
    DriverPerClassExtension::class,
    TextReportExtension::class
)
class SupportSearchTest {

    @Test
    fun `Searching for support topics returns valid results`() {
        val query = "test"
        open(SUPPORT_PAGE)
        element(SUPPORT_SEARCH_INPUT)
            .value = query

        element(SUPPORT_SEARCH_BUTTON)
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Search results for $query"))

        element(SUPPORT_ARTICLES_FOUND)
            .shouldHave(text("1 article found"))

        element(SUPPORT_FIRST_RESULT_TEXT)
            .shouldHave(
                text(
                    "Follow the guide here: https://veriff.me/developers " +
                            "and you'll be able to test out our service. "
                )
            )
    }
}
