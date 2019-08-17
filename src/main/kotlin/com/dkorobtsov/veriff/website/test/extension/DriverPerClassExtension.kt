package com.dkorobtsov.veriff.website.test.extension

import com.dkorobtsov.veriff.website.test.config.applyDefaultConfiguration
import com.dkorobtsov.veriff.website.test.config.configureRemoteWebdriver
import com.dkorobtsov.veriff.website.test.config.releaseWebDriver

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext


class DriverPerClassExtension : BeforeAllCallback, AfterAllCallback {

    override fun beforeAll(context: ExtensionContext?) {
        applyDefaultConfiguration()
        configureRemoteWebdriver()
    }

    override fun afterAll(context: ExtensionContext?) {
        releaseWebDriver()
    }

}
