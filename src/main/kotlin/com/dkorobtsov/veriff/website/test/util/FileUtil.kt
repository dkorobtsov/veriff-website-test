package com.dkorobtsov.veriff.website.test.util

import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

/**
 * Gets test resources by file name
 * @param name name of the file located inside src/test/resources folder
 * @return file matching requested name
 */
@Throws(IOException::class)
fun testResource(name: String): File {
    val location = File(System.getProperty("user.dir"), "src/test/resources/")
    return File(location, name)
}

/**
 * Method allows to handle test resource as BufferedImage
 * @param name name of the file located inside src/test/resources folder
 * @return returns file matching requested name as BufferedImage
 */
@Throws(IOException::class)
fun imageFromTestResource(name: String): BufferedImage {
    return ImageIO.read(testResource(name))
}
