package com.dkorobtsov.veriff.website.test.util

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun testResource(name: String): File {
    val location = File(System.getProperty("user.dir"), "src/test/resources/")
    return File(location, name)
}

fun imageFromTestResource(file: String): BufferedImage {
    return ImageIO.read(testResource(file))
}
