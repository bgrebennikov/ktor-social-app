package com.github.bgrebennikov.utils

import com.github.bgrebennikov.common.thumbnailSizes
import org.imgscalr.Scalr
import java.awt.image.BufferedImage
import kotlin.math.abs
import kotlin.math.min

object ImageUtils {

    private fun cropAsSquare(img: BufferedImage): BufferedImage {
        val resolution = min(img.width, img.height)
        val image = Scalr.resize(
            img, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
            (img.width + (resolution / 2)), (img.height + (resolution / 2)), Scalr.OP_ANTIALIAS
        )

        val x = if (img.width > img.height)
            img.width / 2 else abs(image.width - img.width) / 2

        val y = if (img.width > img.height)
            abs(image.height - img.height) / 2 else abs(image.height - img.height)

        return Scalr.crop(image, x, y, resolution, resolution)
    }

    private fun resize(image: BufferedImage, targetWidth: Int, targetHeight: Int): BufferedImage {
        return Scalr.resize(cropAsSquare(image), targetWidth, targetHeight)
    }

    fun createThumbnails(photo: BufferedImage): Map<String, BufferedImage> {
        val createdThumbnails: MutableMap<String, BufferedImage> = mutableMapOf()
        thumbnailSizes.forEach { size ->

            val resizedImage = resize(photo, size.value, size.value)
            createdThumbnails[size.key] = resizedImage
        }
        return createdThumbnails
    }


}