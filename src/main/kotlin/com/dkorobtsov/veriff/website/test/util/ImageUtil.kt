package com.dkorobtsov.veriff.website.test.util

import ru.yandex.qatools.ashot.Screenshot
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

const val DEFAULT_IMAGE_FORMAT = "png"

fun imageToFile(image: BufferedImage?, path: String) {
    image?.let {
        ImageIO.write(it, DEFAULT_IMAGE_FORMAT, File(path))
    }
}

fun screenshotToFile(screenshot: Screenshot?, path: String) {
    screenshot?.let {
        ImageIO.write(it.image, DEFAULT_IMAGE_FORMAT, File(path))
    }
}

fun imageToByteArray(image: BufferedImage?): ByteArray? {
    image?.let {
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, DEFAULT_IMAGE_FORMAT, baos)
        return baos.toByteArray()
    }
    return null
}