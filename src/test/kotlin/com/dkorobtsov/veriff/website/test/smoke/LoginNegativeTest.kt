package com.dkorobtsov.veriff.website.test.smoke

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.dkorobtsov.veriff.website.test.constants.*
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Test

@Epic(EPIC_VERIFF_WEBSITE)
@Feature(FEATURE_LOGIN)
@Story(STORY_NEGATIVE_CHECKS)
class LoginNegativeTest : BaseWebdriverTest() {

    @Test
    fun `Entering invalid login credentials returns error`() {
        open(LOGIN_PAGE)

        element(LOGIN_USER_EMAIL_FIELD)
            .value = RandomStringUtils.randomAlphabetic(10)

        element(LOGIN_USER_PASSWORD_FIELD)
            .value = RandomStringUtils.randomAlphabetic(10)

        element(LOGIN_GREEN_BUTTON)
            .click()

        element(ALERT_POPUP)
            .shouldHave(text("Invalid Email or password."))

        element(ALERT_BUTTON_CLOSE)
            .shouldBe(visible)
    }

}

