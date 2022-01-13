package com.realtomjoney.setpixeltest

import android.graphics.Bitmap
import android.graphics.Color
import java.util.*

class FloodFillAlgorithm(private val bitmap: Bitmap, private val color: Int = Color.BLACK) {
    fun compute(seed: XYPosition) {
        val colorAtSeed = bitmap.getPixel(seed.x, seed.y)

        val queue = LinkedList<XYPosition>()

        queue.offer(seed)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (bitmap.getPixel(current!!.x, current.y) != colorAtSeed) {
                continue
            }

            bitmap.setPixel(current.x, current.y, color)

            val expandToNeighborsAlgorithmInstance = ExpandToNeighborsAlgorithm(bitmap)
            for (index in expandToNeighborsAlgorithmInstance.compute(current)) {
                queue.offer(index)
            }
        }
    }
}