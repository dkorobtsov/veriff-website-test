package com.dkorobtsov.veriff.website.test.util

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.WebDriverRunner
import com.dkorobtsov.veriff.website.test.constants.COOKIES_BUTTON
import com.dkorobtsov.veriff.website.test.constants.PAGE_HEADER
import com.dkorobtsov.veriff.website.test.constants.PAGE_LOAD_TIMEOUT
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import java.nio.charset.StandardCharsets

/**
 * Returns instance of webdriver attached to current thread
 */
fun driver(): WebDriver {
    return WebDriverRunner.getWebDriver()
}

/**
 * Switches webdriver context to last opened window
 */
@Suppress("RemoveExplicitTypeArguments")
fun switchToNewWindow() {
    val driver = driver()
    val window = driver.windowHandles.stream()
        .reduce { _, b -> b }
        // For some mysterious reason IntellJ randomly
        // highlights AssertionError as compilation error (Throwable is required)
        // Forcing type alias as workaround
        .orElseThrow<AssertionError> { AssertionError("Expected to find new window.") }

    driver
        .switchTo()
        .window(window)
}

/**
 * Returns screenshot with browser window contents as ByteArray
 */
fun getScreenshotBytes(): ByteArray {
    return (driver() as TakesScreenshot)
        .getScreenshotAs(OutputType.BYTES)
}

/**
 * Returns source of currently opened in browser page as ByteArray
 */
fun getPageSourceBytes(): ByteArray {
    return driver()
        .pageSource
        .toByteArray(StandardCharsets.UTF_8)
}

/**
 * Helper method to dismiss cookies consent toolbar.
 */
fun acceptCookiesIfNeeded() {
    element(PAGE_HEADER)
        .waitUntil(Condition.visible, PAGE_LOAD_TIMEOUT)

    // I know it's ugly solution and usually
    // I would rather inject "CookieConsent=true" cookie,
    // but at the moment this temporary hack will do just fine
    val button = element(By.xpath(COOKIES_BUTTON))
    if (button.exists()) {
        element(By.xpath(COOKIES_BUTTON))
            .click()
    }
}

