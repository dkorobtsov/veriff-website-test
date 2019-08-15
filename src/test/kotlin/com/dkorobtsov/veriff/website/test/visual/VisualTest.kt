package com.dkorobtsov.veriff.website.test.visual

import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.junit5.TextReportExtension
import com.dkorobtsov.veriff.website.test.constants.*
import com.dkorobtsov.veriff.website.test.extension.SelenideConfigExtension
import com.dkorobtsov.veriff.website.test.util.acceptCookiesIfNeeded
import com.dkorobtsov.veriff.website.test.visualcheck.Page
import com.dkorobtsov.veriff.website.test.visualcheck.assertPageMatchesWithBaseline
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@Epic(EPIC_VERIFF_WEBSITE)
@Story(STORY_VISUAL_CHECK)
@ExtendWith(
    SelenideConfigExtension::class,
    TextReportExtension::class
)
class VisualTest {

    @Feature(FEATURE_MAIN_PAGE)
    @Test
    fun `Main Page layout matches baseline`() {
        open(MAIN_PAGE)

        acceptCookiesIfNeeded()

        assertPageMatchesWithBaseline(Page.MAIN)
    }

    @Feature(FEATURE_MAIN_PAGE)
    @Test
    @Description("Test expected to fail, baseline corrupted for demo purposes")
    fun `Main Page layout matches baseline (broken)`() {
        open(MAIN_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.MAIN_BROKEN)
    }

    @Feature(FEATURE_PRODUCT)
    @Test
    fun `"Product Page" layout matches baseline`() {
        open(PRODUCT_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.PRODUCT)
    }

    @Feature(FEATURE_CASE_STUDIES)
    @Test
    fun `"Case Studies" Page layout matches baseline`() {
        open(CASE_STUDIES_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.CASE_STUDIES)
    }

    @Feature(FEATURE_COMPANY)
    @Test
    fun `"Company" Page layout matches baseline`() {
        open(COMPANY_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.COMPANY)
    }

    @Feature(FEATURE_CAREERS)
    @Test
    fun `"Careers" Page layout matches baseline`() {
        open(CAREERS_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.CAREERS)
    }

    @Feature(FEATURE_DEVELOPERS)
    @Test
    fun `"Developers" Page layout matches baseline`() {
        open(DEVELOPERS_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.DEVELOPERS)
    }

    @Feature(FEATURE_SUPPORT)
    @Test
    fun `"Support" Page layout matches baseline`() {
        open(SUPPORT_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.SUPPORT)
    }

    @Feature(FEATURE_CONTACT_SALES)
    @Test
    fun `"Contact Sales" Page layout matches baseline`() {
        open(CONTACT_SALES_PAGE)
        acceptCookiesIfNeeded()
        assertPageMatchesWithBaseline(Page.CONTACT_SALES)
    }

    @Feature(FEATURE_FORGOT_PASSWORD)
    @Test
    fun `"Forgot Password" Page layout matches baseline`() {
        open(FORGOT_PASSWORD_PAGE)
        assertPageMatchesWithBaseline(Page.FORGOT_PASSWORD)
    }

    @Feature(FEATURE_LOGIN)
    @Test
    fun `"Login" Page layout matches baseline`() {
        open(LOGIN_PAGE)
        assertPageMatchesWithBaseline(Page.LOGIN_PAGE)
    }

}
