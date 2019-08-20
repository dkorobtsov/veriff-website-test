package com.dkorobtsov.veriff.website.test.extension

import com.dkorobtsov.veriff.website.test.config.applyDefaultConfiguration
import com.dkorobtsov.veriff.website.test.config.configureRemoteWebdriver
import com.dkorobtsov.veriff.website.test.config.releaseWebDriver
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext


/**
 * JUnit5 Extension configuring WebDriver to run each test method inside class in separate browser session
 */
class DriverPerMethodExtension : BeforeAllCallback, BeforeEachCallback, AfterEachCallback {

    override fun beforeAll(context: ExtensionContext?) {
        applyDefaultConfiguration()
    }

    override fun beforeEach(context: ExtensionContext?) {
        configureRemoteWebdriver()
    }

    override fun afterEach(context: ExtensionContext?) {
        releaseWebDriver()
    }

}
