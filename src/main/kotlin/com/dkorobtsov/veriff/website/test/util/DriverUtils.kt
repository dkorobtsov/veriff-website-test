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


fun driver(): WebDriver {
    return WebDriverRunner.getWebDriver()
}

// For some mysterious reason IntellJ randomly
// highlights AssertionError as compilation error (Throwable is required)
// Forcing type alias as workaround
@Suppress("RemoveExplicitTypeArguments")
fun switchToNewWindow() {
    val driver = WebDriverRunner.getWebDriver()
    val window = driver.windowHandles.stream()
        .reduce { _, b -> b }
        .orElseThrow<AssertionError> { AssertionError("Expected to find new window.") }

    driver
        .switchTo()
        .window(window)
}

fun getScreenshotBytes(): ByteArray {
    return (WebDriverRunner.getWebDriver() as TakesScreenshot)
        .getScreenshotAs(OutputType.BYTES)
}

fun getPageSourceBytes(): ByteArray {
    return WebDriverRunner.getWebDriver()
        .pageSource
        .toByteArray(StandardCharsets.UTF_8)
}

// I know it's ugly solution and usually
// I would rather inject "CookieConsent=true" cookie,
// but at the moment this temporary hack will do just fine
fun acceptCookiesIfNeeded() {
    element(PAGE_HEADER)
        .waitUntil(Condition.visible, PAGE_LOAD_TIMEOUT)

    val button = element(By.xpath(COOKIES_BUTTON))
    if (button.exists()) {
        element(By.xpath(COOKIES_BUTTON))
            .click()
    }
}

