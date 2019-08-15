package com.dkorobtsov.veriff.website.test.smoke

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.junit5.TextReportExtension
import com.dkorobtsov.veriff.website.test.constants.*
import com.dkorobtsov.veriff.website.test.extension.DriverPerClassExtension
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@Epic(EPIC_VERIFF_WEBSITE)
@Feature(FEATURE_LOGIN)
@Story(STORY_NEGATIVE_CHECKS)
@ExtendWith(
    DriverPerClassExtension::class,
    TextReportExtension::class
)
class LoginNegativeTest {

    @Test
    fun `Entering invalid login credentials returns error`() {
        open(LOGIN_PAGE)

        element(USER_EMAIL_FIELD)
            .value = RandomStringUtils.randomAlphabetic(10)

        element(USER_PASSWORD_FIELD)
            .value = RandomStringUtils.randomAlphabetic(10)

        element(GREEN_BUTTON)
            .click()

        element(ALERT_POPUP)
            .shouldHave(text("Invalid Email or password."))

        element(ALERT_BUTTON_CLOSE)
            .shouldBe(visible)
    }

}

