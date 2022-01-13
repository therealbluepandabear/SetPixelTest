package com.realtomjoney.setpixeltest

import android.graphics.Bitmap
import android.graphics.Color
import java.util.*

class ExpandToNeighborsAlgorithm(private val bitmap: Bitmap) {
    fun compute(from: XYPosition): List<XYPosition> {
        val toReturn = mutableListOf<XYPosition>()

        if (from.x > 0) toReturn.add(XYPosition(from.x - 1, from.y))

        if (from.x < bitmap.width - 1) toReturn.add(XYPosition(from.x + 1, from.y))

        if (from.y > 0) toReturn.add(XYPosition(from.x, from.y - 1))

        if (from.y < bitmap.width - 1) toReturn.add(XYPosition(from.x, from.y + 1))

        return toReturn
    }
}