package com.dkorobtsov.veriff.website.test.visualcheck

enum class Page(private val baseline: String) {
    MAIN("veriff_main.png"),
    MAIN_BROKEN("veriff_main_broken.png"), //expected to fail
    PRODUCT("veriff_product.png"),
    CASE_STUDIES("veriff_case_studies.png"),
    COMPANY("veriff_company.png"),
    CAREERS("veriff_careers.png"),
    DEVELOPERS("veriff_developers.png"),
    SUPPORT("veriff_support.png"),
    LOGIN_PAGE("veriff_login.png"),
    FORGOT_PASSWORD("veriff_forgot_password.png"),
    CONTACT_SALES("veriff_contact_sales.png"),
    VERIFF_TIMES("veriff_times.png"),
    PRIVACY_POLICY("privacy_policy.png"),
    TERMS_AND_CONDITIONS("terms_and_conditions.png"),
    RESPONSIBLE_DISCLOSURE("responsible_disclosure.png");

    fun baseline(): String {
        return this.baseline
    }
}
