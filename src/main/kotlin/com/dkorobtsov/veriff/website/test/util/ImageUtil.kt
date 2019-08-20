package com.dkorobtsov.veriff.website.test.util

import ru.yandex.qatools.ashot.Screenshot
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

const val DEFAULT_SCREENSHOTS_DIRECTORY = "screenshots"
const val DEFAULT_IMAGE_FORMAT = "png"

/**
 * Saves BufferedImage to file inside default screenshots folder
 */
fun imageToFile(image: BufferedImage?, path: String) {
    val dir = createScreenshotsDirIfDoesNotExist()
    image?.let {
        ImageIO.write(it, DEFAULT_IMAGE_FORMAT, File(dir, path))
    }
}

/**
 * Saves aShot screenshot to file inside default screenshots folder
 */
fun screenshotToFile(screenshot: Screenshot?, fileName: String) {
    val dir = createScreenshotsDirIfDoesNotExist()
    screenshot?.let {
        ImageIO.write(it.image, DEFAULT_IMAGE_FORMAT, File(dir, fileName))
    }
}

/**
 * Returns BufferedImage as ByteArray
 */
fun imageToByteArray(image: BufferedImage?): ByteArray? {
    image?.let {
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, DEFAULT_IMAGE_FORMAT, baos)
        return baos.toByteArray()
    }
    return null
}

/**
 * Helper method to create folder for saving screenshots and diff images
 */
private fun createScreenshotsDirIfDoesNotExist(): File {
    val dir = File(DEFAULT_SCREENSHOTS_DIRECTORY)
    when {
        !dir.exists() -> dir.mkdirs()
    }
    return dir
}
