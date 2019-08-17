package com.dkorobtsov.veriff.website.test.smoke


import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.junit5.TextReportExtension
import com.dkorobtsov.veriff.website.test.constants.*
import com.dkorobtsov.veriff.website.test.extension.DriverPerClassExtension
import com.dkorobtsov.veriff.website.test.util.acceptCookiesIfNeeded
import com.dkorobtsov.veriff.website.test.util.switchToNewWindow
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@Epic(EPIC_VERIFF_WEBSITE)
@Story(STORY_NAVIGATION)
@ExtendWith(
    DriverPerClassExtension::class,
    TextReportExtension::class
)
class NavigationTest {

    @Feature(FEATURE_MAIN_PAGE)
    @Test
    fun `Click on logo opens main page`() {
        open(PRODUCT_PAGE)
        element(PAGE_HEADER)
            .shouldHave(text("Trust is everything. Make it bulletproof."))

        element(HEADER_VERIFF_LOGO)
            .shouldBe(visible)
            .shouldBe(enabled)
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Immunity from digital fraud"))
    }

    @Feature(FEATURE_MAIN_PAGE)
    @Test
    fun `Main page header contains all necessary links`() {
        open(MAIN_PAGE)
        element(HEADER_PRODUCT_LINK).shouldBe(visible)
        element(HEADER_COMPANY_LINK).shouldBe(visible)
        element(HEADER_CAREERS_LINK).shouldBe(visible)
        element(HEADER_SUPPORT_LINK).shouldBe(visible)
        element(HEADER_SIGN_IN_LINK).shouldBe(visible)
        element(HEADER_DEVELOPERS_LINK).shouldBe(visible)
        element(HEADER_CASE_STUDIES_LINK).shouldBe(visible)
        element(HEADER_CONTACT_SALES_LINK).shouldBe(visible)
    }

    @Feature(FEATURE_MAIN_PAGE)
    @Test
    fun `Main page footer contains all necessary links`() {
        open(MAIN_PAGE)
        element(FOOTER_CONTACT_EMAIL).shouldBe(visible)
        element(FOOTER_PRIVACY_POLICY_LINK).shouldBe(visible)
        element(FOOTER_TERMS_AND_CONDITIONS_LINK).shouldBe(visible)
        element(FOOTER_RESPONSIBLE_DISCLOSURE_LINK).shouldBe(visible)
        element(FOOTER_VERIFF_TIMES_LINK).shouldBe(visible)
    }

    @Feature(FEATURE_PRODUCT)
    @Test
    fun `"Product" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_PRODUCT_LINK)
            .shouldBe(visible)
            .shouldHave(text("Product"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Trust is everything. Make it bulletproof."))
    }

    @Feature(FEATURE_SUPPORT)
    @Test
    fun `"Support" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_SUPPORT_LINK)
            .shouldBe(visible)
            .shouldHave(text("Support"))
            .click()

        switchToNewWindow()
        element(PAGE_HEADER)
            .shouldHave(text("Veriff Knowledge Base"))
    }

    @Feature(FEATURE_CAREERS)
    @Test
    fun `"Careers" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_CAREERS_LINK)
            .shouldBe(visible)
            .shouldHave(text("Careers"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Join Veriff"))
    }

    @Feature(FEATURE_CONTACT_SALES)
    @Test
    fun `"Contact Sales" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_CONTACT_SALES_LINK)
            .shouldBe(visible)
            .shouldHave(text("Contact sales"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Is Veriff right for your needs?"))
    }

    @Feature(FEATURE_DEVELOPERS)
    @Test
    fun `"Developers" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_DEVELOPERS_LINK)
            .shouldBe(visible)
            .shouldHave(text("Developers"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Contract signed?"))
    }

    @Feature(FEATURE_CASE_STUDIES)
    @Test
    fun `"Case Studies" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_CASE_STUDIES_LINK)
            .shouldBe(visible)
            .shouldHave(text("Case studies"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Case studies"))
    }

    @Feature(FEATURE_COMPANY)
    @Test
    fun `"Company" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_COMPANY_LINK)
            .shouldBe(visible)
            .shouldHave(text("Company"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Meet Veriff"))
    }

    @Feature(FEATURE_PRIVACY_POLICY)
    @Test
    fun `"Privacy Policy" link leads to the correct page`() {
        open(MAIN_PAGE)
        acceptCookiesIfNeeded()
        element(FOOTER_PRIVACY_POLICY_LINK)
            .shouldBe(visible)
            .shouldHave(text("Privacy policy"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Privacy policy"))
    }

    @Feature(FEATURE_TERMS_AND_CONDITIONS)
    @Test
    fun `"Terms and Conditions" link leads to the correct page`() {
        open(MAIN_PAGE)
        acceptCookiesIfNeeded()
        element(FOOTER_TERMS_AND_CONDITIONS_LINK)
            .shouldBe(visible)
            .shouldHave(text("Terms & conditions"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Terms & conditions"))
    }

    @Feature(FEATURE_RESPONSIBLE_DISCLOSURE)
    @Test
    fun `"Responsible Disclosure" link leads to the correct page`() {
        open(MAIN_PAGE)
        acceptCookiesIfNeeded()
        element(FOOTER_RESPONSIBLE_DISCLOSURE_LINK)
            .shouldBe(visible)
            .shouldHave(text("Responsible disclosure"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("Responsible Disclosure"))
    }

    @Feature(FEATURE_VERIFF_TIMES)
    @Test
    fun `"Veriff Times" link leads to the company blog`() {
        open(MAIN_PAGE)
        acceptCookiesIfNeeded()
        element(FOOTER_VERIFF_TIMES_LINK)
            .shouldBe(visible)
            .shouldHave(text("The Veriff Times"))
            .click()

        element(PAGE_HEADER)
            .shouldHave(text("The Veriff Times."))
    }

    @Feature(FEATURE_LOGIN)
    @Test
    fun `"Sign in" link leads to the correct page`() {
        open(MAIN_PAGE)
        element(HEADER_SIGN_IN_LINK)
            .shouldBe(visible)
            .shouldHave(text("Sign in"))

        element(HEADER_SIGN_IN_LINK)
            .click()

        element(LOGIN_PAGE_HEADER)
            .shouldBe(visible)
            .shouldHave(text("Log in"))

        element(USER_EMAIL_FIELD)
            .shouldBe(visible)
            .shouldBe(enabled)

        element(USER_PASSWORD_FIELD)
            .shouldBe(visible)
            .shouldBe(enabled)

        element(GREEN_BUTTON)
            .shouldBe(visible)
            .shouldBe(enabled)
            .shouldHave(value("Log in"))
    }

    @Feature(FEATURE_FORGOT_PASSWORD)
    @Test
    fun `"Forgot Password" link leads to the correct page`() {
        open(LOGIN_PAGE)
        element(FORGOT_PASSWORD_BUTTON)
            .shouldBe(visible)
            .shouldBe(enabled)

        element(FORGOT_PASSWORD_BUTTON)
            .click()

        element(LOGIN_PAGE_HEADER)
            .shouldBe(visible)
            .shouldHave(text("Forgot password?"))

        element(USER_EMAIL_FIELD)
            .shouldBe(visible)
            .shouldBe(enabled)

        element(GREEN_BUTTON)
            .shouldBe(visible)
            .shouldBe(enabled)
            .shouldHave(value("Send instructions"))

        element(CANCEL_BUTTON)
            .shouldBe(visible)
            .shouldBe(enabled)
            .shouldHave(text("Cancel"))
    }

}
