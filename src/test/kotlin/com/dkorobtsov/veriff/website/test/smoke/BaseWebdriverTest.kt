package com.dkorobtsov.veriff.website.test.smoke

import com.codeborne.selenide.junit5.TextReportExtension
import com.dkorobtsov.veriff.website.test.extension.DriverPerClassExtension
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(
    DriverPerClassExtension::class,
    TextReportExtension::class
)
abstract class BaseWebdriverTest
