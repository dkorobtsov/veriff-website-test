package com.dkorobtsov.veriff.website.test.smoke

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.dkorobtsov.veriff.website.test.constants.*
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test

@Epic(EPIC_VERIFF_WEBSITE)
@Feature(FEATURE_LOGIN)
@Story(STORY_NAVIGATION)
class LoginPageTest : BaseWebdriverTest() {

    @Test
    fun `"Forgot Password" link leads to the correct page`() {
        open(LOGIN_PAGE)

        element(LOGIN_FORGOT_PASSWORD_BUTTON)
            .shouldBe(Condition.visible)
            .shouldBe(Condition.enabled)

        element(LOGIN_FORGOT_PASSWORD_BUTTON)
            .click()

        element(LOGIN_PAGE_HEADER)
            .shouldBe(Condition.visible)
            .shouldHave(text("Forgot password?"))

        element(LOGIN_USER_EMAIL_FIELD)
            .shouldBe(Condition.visible)
            .shouldBe(Condition.enabled)

        element(LOGIN_GREEN_BUTTON)
            .shouldBe(Condition.visible)
            .shouldBe(Condition.enabled)
            .shouldHave(Condition.value("Send instructions"))

        element(LOGIN_CANCEL_BUTTON)
            .shouldBe(Condition.visible)
            .shouldBe(Condition.enabled)
            .shouldHave(text("Cancel"))
    }

}

