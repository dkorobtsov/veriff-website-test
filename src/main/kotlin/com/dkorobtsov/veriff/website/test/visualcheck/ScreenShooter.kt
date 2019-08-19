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
import java.util.*


private const val DEFAULT_IMAGE_MEDIA_TYPE = "image/png"
private const val DIFF_PREFIX = "diff-"
private val config = ConfigFactory.create(SuiteConfig::class.java)

@Step("Assert that page layout matches with baseline")
fun assertPageMatchesWithBaseline(page: Page) {
    hideScrollbar()
    val screenshot = takeFullPageScreenshot(page.ignoredElements())

    if (config.isDebug()) {
        screenshotToFile(screenshot, page.baseline())
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
                DIFF_PREFIX + page.baseline()
            )
        }

        Assertions.fail<String>("Page layout does not match with baseline.")
    }
}

private fun hideScrollbar() {
    Selenide.executeJavaScript<Any>("document.body.style.overflow = 'hidden';")
}

fun takeFullPageScreenshot(ignoredElements: List<By?>): Screenshot? {
    return AShot()
        .ignoredAreas(getCoordinates(ignoredElements))
        .shootingStrategy(
            ShootingStrategies
                .viewportNonRetina(100, 0, 0)
        )
        .takeScreenshot(driver().webDriver)
}

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
