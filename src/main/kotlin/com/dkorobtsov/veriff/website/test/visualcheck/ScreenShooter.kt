package com.dkorobtsov.veriff.website.test.visualcheck

import com.dkorobtsov.veriff.website.test.config.SuiteConfig
import com.dkorobtsov.veriff.website.test.util.*
import io.qameta.allure.Allure
import io.qameta.allure.Step
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.Assertions
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.comparison.ImageDiffer
import ru.yandex.qatools.ashot.shooting.ShootingStrategies


private const val DEFAULT_IMAGE_MEDIA_TYPE = "image/png"
private const val DIFF_IMAGE_FILE_NAME = "diff.png"
private const val PAGE_SCREENSHOT_FILE_NAME = "screenshot.png"
private val config = ConfigFactory.create(SuiteConfig::class.java)

fun takeFullPageScreenshot(): Screenshot? {
    return AShot()
        .shootingStrategy(
            ShootingStrategies
                .viewportPasting(100)
        )
        .takeScreenshot(driver())
}


@Step("Assert that page layout matches with baseline")
fun assertPageMatchesWithBaseline(page: Page) {
    val screenshot = takeFullPageScreenshot()

    if (config.isDebug()) {
        screenshotToFile(
            screenshot,
            PAGE_SCREENSHOT_FILE_NAME
        )
    }

    val baseline = Screenshot(imageFromTestResource(page.baseline()))
    val diff = ImageDiffer().makeDiff(screenshot, baseline)
    val diffImage = diff.markedImage

    if (diff.hasDiff()) {
        imageToByteArray(diffImage)?.let {
            Allure.getLifecycle().addAttachment(
                "Screenshot",
                DEFAULT_IMAGE_MEDIA_TYPE,
                DEFAULT_IMAGE_FORMAT, it
            )
        }

        if (config.isDebug()) {
            imageToFile(
                diffImage,
                DIFF_IMAGE_FILE_NAME
            )
        }

        Assertions.fail<String>("Page layout does not match with baseline.")
    }
}