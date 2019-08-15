package com.dkorobtsov.veriff.website.test.constants

// Typically I do not use constants based approach for UI tests but for
// checking small static web site it feels like appropriate solution

// Page urls
const val MAIN_PAGE = ""
const val PRODUCT_PAGE = "/product"
const val CASE_STUDIES_PAGE = "/case-studies"
const val COMPANY_PAGE = "/company"
const val CAREERS_PAGE = "/careers"
const val DEVELOPERS_PAGE = "/developers"
const val SUPPORT_PAGE = "https://veriff-support.helpscoutdocs.com"
const val CONTACT_SALES_PAGE = "/contact-sales"
const val FORGOT_PASSWORD_PAGE = "https://office.veriff.me/password/new"
const val LOGIN_PAGE = "https://office.veriff.me/login"
const val LOGIN_PAGE_HEADER = ".login-heading"

// Alert
const val ALERT_POPUP = ".alert-warning"
const val ALERT_BUTTON_CLOSE = ".close"

// Header
const val PAGE_HEADER = "h1"
const val HEADER_PRODUCT_LINK = "a[href^=\"$PRODUCT_PAGE\"]"
const val HEADER_CASE_STUDIES_LINK = "a[href^=\"$CASE_STUDIES_PAGE\"]"
const val HEADER_COMPANY_LINK = "a[href^=\"$COMPANY_PAGE\"]"
const val HEADER_CAREERS_LINK = "a[href^=\"$CAREERS_PAGE\"]"
const val HEADER_DEVELOPERS_LINK = "a[href^=\"$DEVELOPERS_PAGE\"]"
const val HEADER_SUPPORT_LINK = "a[href=\"https://veriff-support.helpscoutdocs.com/\"]"
const val HEADER_SIGN_IN_LINK = "a[href=\"https://office.veriff.me/login\"]"
const val HEADER_CONTACT_SALES_LINK = "a[href^=\"$CONTACT_SALES_PAGE\"]"
const val COOKIES_BUTTON = "//button[text()='I understand']"

// Login page elements
const val USER_PASSWORD_FIELD = "#user_password"
const val USER_EMAIL_FIELD = "#user_email"
const val GREEN_BUTTON = ".btn-default"
const val CANCEL_BUTTON = "a[href^=\"/login\"]"
const val FORGOT_PASSWORD_BUTTON = "a[href^=\"/password/new\"]"

//Timeout
const val PAGE_LOAD_TIMEOUT: Long = 4_000
