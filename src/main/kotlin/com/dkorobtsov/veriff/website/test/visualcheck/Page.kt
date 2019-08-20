package com.dkorobtsov.veriff.website.test.visualcheck

import com.dkorobtsov.veriff.website.test.constants.FORGOT_PASSWORD_EMAIL_FIELD
import com.dkorobtsov.veriff.website.test.constants.LOGIN_USER_EMAIL_FIELD
import com.dkorobtsov.veriff.website.test.constants.LOGIN_USER_PASSWORD_FIELD
import com.dkorobtsov.veriff.website.test.constants.SUPPORT_SEARCH_INPUT
import org.openqa.selenium.By
import org.openqa.selenium.By.cssSelector

/**
 * Class holds references to baseline images and excluded elements for specific pages
 */
enum class Page(
    private val baseline: String,
    private vararg val ignoredElements: By?
) {

    MAIN(
        "veriff_main.png"
    ),
    MAIN_BROKEN(
        //expected to fail
        "veriff_main_broken.png"
    ),
    PRODUCT(
        "veriff_product.png"
    ),
    CASE_STUDIES(
        "veriff_case_studies.png"
    ),
    COMPANY(
        "veriff_company.png"
    ),
    CAREERS(
        "veriff_careers.png"
    ),
    DEVELOPERS(
        "veriff_developers.png"
    ),
    SUPPORT(
        "veriff_support.png",
        // Blinking cursor randomly causes test to fail
        cssSelector(SUPPORT_SEARCH_INPUT)
    ),
    LOGIN_PAGE(
        "veriff_login.png",
        // Blinking cursor randomly causes test to fail
        cssSelector(LOGIN_USER_PASSWORD_FIELD),
        cssSelector(LOGIN_USER_EMAIL_FIELD)
    ),
    FORGOT_PASSWORD(
        "veriff_forgot_password.png",
        // Blinking cursor randomly causes test to fail
        cssSelector(FORGOT_PASSWORD_EMAIL_FIELD)
    ),
    CONTACT_SALES(
        "veriff_contact_sales.png"
    ),
    VERIFF_TIMES(
        "veriff_times.png"
    ),
    PRIVACY_POLICY(
        "veriff_privacy_policy.png"
    ),
    TERMS_AND_CONDITIONS(
        "veriff_terms_and_conditions.png"
    ),
    RESPONSIBLE_DISCLOSURE(
        "veriff_responsible_disclosure.png"
    );

    /**
     * As a baseline we are using full page screenshot in .png format
     * stored in test resources folder: src/test/resources
     */
    fun baseline(): String {
        return this.baseline
    }

    /**
     * Non stable page elements producing different results on every screenshots
     * that we want to exclude from comparison. For example: input fields, where
     * blinking cursor makes image comparison based tests to fail.
     */
    fun ignoredElements(): List<By?> {
        return this.ignoredElements.let {
            it
                .asList()
                .filterNotNull()
        }
    }

}
