package com.dkorobtsov.veriff.website.test.visualcheck

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.WebDriverRunner.driver
import com.dkorobtsov.veriff.website.test.config.SuiteConfig
import com.dkorobtsov.veriff.website.test.util.*
import io.qameta.allure.Allure
import io.qameta.allure.Step
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.comparison.ImageDiffer
import ru.yandex.qatools.ashot.coordinates.Coords
import ru.yandex.qatools.ashot.shooting.ShootingStrategies
import java.awt.image.BufferedImage
import java.util.*


private const val DEFAULT_IMAGE_MEDIA_TYPE = "image/png"
private const val DIFF_PREFIX = "diff-"
private val config = ConfigFactory.create(SuiteConfig::class.java)

/**
 * Helper method to take full page screenshot.
 * Works as follows: webdriver scrolls page from up to down, taking screenshot after each scroll.
 * All taken screenshots are stitched together.
 * @param ignoredElements collection on Selenium By locators that should be ignored during comparison
 * @return full page screenshot
 */
fun takeFullPageScreenshot(ignoredElements: List<By?>): Screenshot? {
    return AShot()
        .ignoredAreas(getCoordinates(ignoredElements))
        .shootingStrategy(
            ShootingStrategies
                .viewportNonRetina(100, 0, 0)
        )
        .takeScreenshot(driver().webDriver)
}

/**
 * Helper method to assert if inspected page matches with baseline.
 * If any differences were found, behavior is following:
 * 1. Image file with marked differences attached to Allure report.
 * 2. If debug mode is enabled, new screenshot and copy of diff image are saved to /screenshots folder
 * 3. AssertionFailedError is thrown marking test as failed.
 */
@Step("Assert that page layout matches with baseline")
fun assertPageMatchesWithBaseline(page: Page) {
    hideScrollbar()
    val screenshot = takeFullPageScreenshot(page.ignoredElements())
    val baseline = Screenshot(imageFromTestResource(page.baseline()))
    val diff = ImageDiffer().makeDiff(screenshot, baseline)
    val diffImage = diff.markedImage //image with marked differences

    if (diff.hasDiff()) {
        attachDiffImageToAllureReport(diffImage)

        if (config.isDebug()) {
            screenshotToFile(screenshot, page.baseline())
            imageToFile(diffImage, DIFF_PREFIX + page.baseline())
        }

        Assertions.fail<String>("Page layout does not match with baseline.")
    }
}

/**
 * Helper method to attach diff image to Allure test execution report
 */
private fun attachDiffImageToAllureReport(diffImage: BufferedImage?) {
    imageToByteArray(diffImage)?.let {
        Allure.getLifecycle().addAttachment(
            "Screenshot",
            DEFAULT_IMAGE_MEDIA_TYPE,
            DEFAULT_IMAGE_FORMAT, it
        )
    }
}

/**
 * Helper method to hide page scrollbars (helps to reduce flakiness and get cleaner screenshots)
 */
private fun hideScrollbar() {
    Selenide.executeJavaScript<Any>("document.body.style.overflow = 'hidden';")
}

/**
 * Method calculates coordinates for all page areas that should be ignored during comparison
 * @param ignoredElements = collection of Selenium By locators
 */
private fun getCoordinates(ignoredElements: List<By?>): Set<Coords> {
    val ignoredCoordinates = HashSet<Coords>()
    ignoredElements.forEach { by ->
        val element = element(by)
        val dimension = element.size
        val point = element.location
        val coords = Coords(
            point.getX(),
            point.getY(),
            dimension.getWidth(),
            dimension.getHeight()
        )
        ignoredCoordinates.add(coords)
    }
    return ignoredCoordinates
}
