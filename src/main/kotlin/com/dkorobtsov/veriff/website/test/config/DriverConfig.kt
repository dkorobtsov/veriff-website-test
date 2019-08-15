package com.dkorobtsov.veriff.website.test.config

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.logevents.LogEventListener
import com.codeborne.selenide.logevents.SelenideLogger
import com.dkorobtsov.veriff.website.test.report.AllureSelenideListener
import org.aeonbits.owner.ConfigFactory
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.slf4j.bridge.SLF4JBridgeHandler
import java.net.URI

const val ALLURE_LISTENER_NAME = "allure"

private val config = ConfigFactory.create(SuiteConfig::class.java)

fun applyDefaultConfiguration() {
    //Forwarding Selenide log output to local lof4j2 logger
    SLF4JBridgeHandler.removeHandlersForRootLogger()
    SLF4JBridgeHandler.install()

    Configuration.browser = config.targetBrowser()
    Configuration.baseUrl = config.baseUrl()
}

fun configureRemoteWebdriver() {
    val options = ChromeOptions()
    options.setCapability("version", "76.0")
    options.setCapability("enableVNC", true)
    options.setCapability("enableVideo", false)
    options.addArguments("1366x768")
    options.addArguments("window-size=1366,768")
    options.addArguments("disable-infobars")
    options.addArguments("--hide-scrollbars")
    options.addArguments("--disable-extensions")
    options.addArguments("ignore-certificate-errors")
    options.setHeadless(config.isHeadless())
    options.setAcceptInsecureCerts(true)

    val driver = RemoteWebDriver(
        URI.create(config.selenoidUrl()).toURL(),
        options
    )
    WebDriverRunner.setWebDriver(driver)
    SelenideLogger.addListener(ALLURE_LISTENER_NAME, AllureSelenideListener())
}

fun releaseWebDriver() {
    SelenideLogger.removeListener<LogEventListener>(ALLURE_LISTENER_NAME)
    Selenide.close()
}
