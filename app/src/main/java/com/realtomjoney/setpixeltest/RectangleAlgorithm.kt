package com.realtomjoney.setpixeltest

import android.graphics.Bitmap
import android.graphics.Color

class RectangleAlgorithm(private val bitmap: Bitmap, private val currentBitmapAction: BitmapAction, private val color: Int = Color.BLACK) {
    fun compute(from: XYPosition, to: XYPosition) {
        var x = from.x
        val y = from.y

        if (from.x >= to.x && from.y <= to.y) {
            while (x >= to.x) {
                for (i in y..to.y) {
                    currentBitmapAction.actionData.add(BitmapActionData(XYPosition(x, i), bitmap.getPixel(x, i)))
                    bitmap.setPixel(x, i, color)
                }

                bitmap.setPixel(x, y, Color.BLACK)

                x--
            }
        } else if (from.x <= to.x && from.y <= to.y) {
            while (x <= to.x) {
                for (i in y..to.y) {
                    currentBitmapAction.actionData.add(BitmapActionData(XYPosition(x, i), bitmap.getPixel(x, i)))
                    bitmap.setPixel(x, i, Color.BLACK)
                }

                bitmap.setPixel(x, y, Color.BLACK)

                x++
            }
        } else if (from.x <= to.x && from.y >= to.y) {
            while (x <= to.x) {

                for (i in to.y..y) {
                    currentBitmapAction.actionData.add(BitmapActionData(XYPosition(x, i), bitmap.getPixel(x, i)))
                    bitmap.setPixel(x, i, color)
                }

                bitmap.setPixel(x, y, Color.BLACK)

                x++
            }

        } else if (from.x >= to.x && from.y >= to.y) {
            while (x >= to.x) {

                for (i in to.y..y) {
                    currentBitmapAction.actionData.add(BitmapActionData(XYPosition(x, i), bitmap.getPixel(x, i)))
                    bitmap.setPixel(x, i, Color.BLACK)
                }

                bitmap.setPixel(x, y, Color.BLACK)

                x--
            }
        }
    }
}