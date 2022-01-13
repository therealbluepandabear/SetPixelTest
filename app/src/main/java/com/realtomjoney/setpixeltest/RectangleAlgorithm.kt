package com.realtomjoney.setpixeltest

import android.graphics.Bitmap
import android.graphics.Color

class RectangleAlgorithm(private val bitmap: Bitmap) {
    fun compute(from: XYPosition, to: XYPosition) {
        var x = from.x
        val y = from.y

        if (from.x >= to.x && from.y <= to.y) {
            while (x >= to.x) {
                bitmap.setPixel(x, y, Color.BLACK)

                for (i in y..to.y) {
                    bitmap.setPixel(x, i, Color.BLACK)
                }

                x--
            }
        } else if (from.x <= to.x && from.y <= to.y) {
            while (x <= to.x) {
                bitmap.setPixel(x, y, Color.BLACK)

                for (i in y..to.y) {
                    bitmap.setPixel(x, i, Color.BLACK)
                }

                x++
            }
        } else if (from.x <= to.x && from.y >= to.y) {
            while (x <= to.x) {
                bitmap.setPixel(x, y, Color.BLACK)


                for (i in to.y..y) {
                    bitmap.setPixel(x, i, Color.BLACK)
                }

                x++
            }

        } else if (from.x >= to.x && from.y >= to.y) {
            while (x >= to.x) {
                bitmap.setPixel(x, y, Color.BLACK)


                for (i in to.y..y) {
                    bitmap.setPixel(x, i, Color.BLACK)
                }

                x--
            }
        }
    }
}