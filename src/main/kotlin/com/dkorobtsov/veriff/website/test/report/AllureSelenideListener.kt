package com.dkorobtsov.veriff.website.test.report

import com.codeborne.selenide.logevents.LogEvent
import com.codeborne.selenide.logevents.LogEventListener
import com.dkorobtsov.veriff.website.test.util.getPageSourceBytes
import com.dkorobtsov.veriff.website.test.util.getScreenshotBytes
import io.qameta.allure.Allure
import io.qameta.allure.AllureLifecycle
import io.qameta.allure.model.Status
import io.qameta.allure.model.StatusDetails
import io.qameta.allure.model.StepResult
import io.qameta.allure.util.ResultsUtils
import java.util.*

class AllureSelenideListener : LogEventListener {

    private val lifecycle: AllureLifecycle = Allure.getLifecycle()

    override fun afterEvent(currentLog: LogEvent) {
        lifecycle.currentTestCase.ifPresent {
            val stepUUID = UUID.randomUUID().toString()
            lifecycle.startStep(
                stepUUID, StepResult()
                    .setName(currentLog.toString())
                    .setStatus(Status.PASSED)
            )

            lifecycle.updateStep { stepResult ->
                stepResult.start = stepResult.start!! - currentLog.duration
            }

            if (LogEvent.EventStatus.FAIL == currentLog.status) {
                attachEvidences()

                lifecycle.updateStep { stepResult ->
                    val details = ResultsUtils.getStatusDetails(currentLog.error)
                        .orElse(StatusDetails())
                    stepResult.status = Status.FAILED
                    stepResult.statusDetails = details
                }
            }
            lifecycle.stopStep(stepUUID)
        }
    }

    @Suppress("EmptyFunctionBlock")
    override fun beforeEvent(currentLog: LogEvent) {
        // Nothing to do here
    }

    private fun attachEvidences() {
        attachScreenshot()
        attachPageSource()
    }

    private fun attachPageSource() {
        Allure.getLifecycle().addAttachment(
            PAGE_SOURCE,
            "text/plain", "txt",
            getPageSourceBytes()
        )
    }

    private fun attachScreenshot() {
        Allure.getLifecycle().addAttachment(
            SCREENSHOT, "image/png", "png",
            getScreenshotBytes()
        )
    }

    companion object {

        private const val PAGE_SOURCE = "Page source"
        private const val SCREENSHOT = "Screenshot"
    }
}
