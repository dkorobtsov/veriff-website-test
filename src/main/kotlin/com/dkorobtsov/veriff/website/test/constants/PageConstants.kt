package com.dkorobtsov.veriff.website.test.constants

// Typically I do not use constants based approach for UI tests but for
// checking small static web site it feels like appropriate solution

//Timeout
const val PAGE_LOAD_TIMEOUT: Long = 4_000

// Header urls
const val MAIN_PAGE = ""
const val PRODUCT_PAGE = "/product"
const val COMPANY_PAGE = "/company"
const val CAREERS_PAGE = "/careers"
const val DEVELOPERS_PAGE = "/developers"
const val CASE_STUDIES_PAGE = "/case-studies"
const val SUPPORT_PAGE = "https://veriff-support.helpscoutdocs.com"
const val CONTACT_SALES_PAGE = "/contact-sales"
const val LOGIN_PAGE = "https://office.veriff.me/login"

// Footer uls
const val VERIFF_TIMES_PAGE = "/veriff-times"
const val PRIVACY_POLICY_PAGE = "/privacy-policy"
const val TERMS_AND_CONDITIONS_PAGE = "/terms-and-conditions"
const val RESPONSIBLE_DISCLOSURE_PAGE = "/responsible-disclosure"

// Login page urls
const val FORGOT_PASSWORD_PAGE = "https://office.veriff.me/password/new"

// Alert
const val ALERT_POPUP = ".alert-warning"
const val ALERT_BUTTON_CLOSE = ".close"

// Header
const val PAGE_HEADER = "h1"
const val HEADER_VERIFF_LOGO = "#veriff-logo"
const val HEADER_PRODUCT_LINK = "a[href^=\"$PRODUCT_PAGE\"]"
const val HEADER_CASE_STUDIES_LINK = "a[href^=\"$CASE_STUDIES_PAGE\"]"
const val HEADER_COMPANY_LINK = "a[href^=\"$COMPANY_PAGE\"]"
const val HEADER_CAREERS_LINK = "a[href^=\"$CAREERS_PAGE\"]"
const val HEADER_DEVELOPERS_LINK = "a[href^=\"$DEVELOPERS_PAGE\"]"
const val HEADER_SUPPORT_LINK = "a[href^=\"$SUPPORT_PAGE\"]"
const val HEADER_SIGN_IN_LINK = "a[href^=\"$LOGIN_PAGE\"]"
const val HEADER_CONTACT_SALES_LINK = "a[href^=\"$CONTACT_SALES_PAGE\"]"
const val FOOTER_CONTACT_EMAIL = "a[href^=\"mailto:info@veriff.me\"]"
const val FOOTER_VERIFF_TIMES_LINK = "a[href^=\"$VERIFF_TIMES_PAGE\"]"
const val FOOTER_PRIVACY_POLICY_LINK = "a[href^=\"$PRIVACY_POLICY_PAGE\"]"
const val FOOTER_TERMS_AND_CONDITIONS_LINK = "a[href^=\"$TERMS_AND_CONDITIONS_PAGE\"]"
const val FOOTER_RESPONSIBLE_DISCLOSURE_LINK = "a[href^=\"$RESPONSIBLE_DISCLOSURE_PAGE\"]"
const val COOKIES_BUTTON = "//button[text()='I understand']"

// Login page elements
const val LOGIN_PAGE_HEADER = ".login-heading"
const val LOGIN_USER_PASSWORD_FIELD = "#user_password"
const val LOGIN_USER_EMAIL_FIELD = "#user_email"
const val LOGIN_GREEN_BUTTON = ".btn-default"
const val LOGIN_CANCEL_BUTTON = "a[href^=\"/login\"]"
const val LOGIN_FORGOT_PASSWORD_BUTTON = "a[href^=\"/password/new\"]"

// Login page elements
const val FORGOT_PASSWORD_PAGE_HEADER = LOGIN_PAGE_HEADER
const val FORGOT_PASSWORD_EMAIL_FIELD = LOGIN_USER_EMAIL_FIELD
const val FORGOT_PASSWORD_GREEN_BUTTON = LOGIN_GREEN_BUTTON
const val FORGOT_PASSWORD_CANCEL_BUTTON = LOGIN_CANCEL_BUTTON

// Support Page Elements
const val SUPPORT_SEARCH_INPUT = ".search-query"
const val SUPPORT_SEARCH_BUTTON = "button[type=\"submit\"]"
const val SUPPORT_ARTICLES_FOUND = ".articlesFound"
const val SUPPORT_FIRST_RESULT_TEXT = ".articleList p"
