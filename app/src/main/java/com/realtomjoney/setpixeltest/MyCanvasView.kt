package com.realtomjoney.setpixeltest

import android.content.Context
import android.graphics.*
import android.view.View
import android.graphics.Bitmap
import android.view.MotionEvent

class MyCanvasView (context: Context, private var spanCount: Int) : View(context) {
    lateinit var extraCanvas: Canvas
    lateinit var extraBitmap: Bitmap

    private var scaleWidth = 0f
    private var scaleHeight = 0f

    private var prevX: Int? = null
    private var prevY: Int? = null

    private val bitmapActionData: MutableList<BitmapAction> = mutableListOf()
    private var currentBitmapAction: BitmapAction? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        extraBitmap = Bitmap.createBitmap(spanCount, spanCount, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)

        for (i in 0 until spanCount) {
            extraBitmap.setPixel(i, i, Color.YELLOW)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val coordinateX = (event.x / scaleWidth).toInt()
        val coordinateY = (event.y / scaleWidth).toInt()

        if (currentBitmapAction != null) {
            currentBitmapAction!!.actionData.add(BitmapActionData(XYPosition(coordinateX, coordinateY), extraBitmap.getPixel(coordinateX, coordinateY)))
        } else {
            currentBitmapAction = BitmapAction(mutableListOf())
            currentBitmapAction!!.actionData.add(BitmapActionData(XYPosition(coordinateX, coordinateY), extraBitmap.getPixel(coordinateX, coordinateY)))
        }

        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                if (prevX != null && prevY != null) {
                    val lineAlgorithmInstance = LineAlgorithm(extraBitmap, currentBitmapAction!!)

                    lineAlgorithmInstance.compute(XYPosition(prevX!!, prevY!!), XYPosition(coordinateX, coordinateY))
                }

                extraBitmap.setPixel(coordinateX, coordinateY, Color.BLACK)

                prevX = coordinateX
                prevY = coordinateY

                invalidate()
            }
            MotionEvent.ACTION_DOWN -> {
                if (prevX != null && prevY != null) {
                    val lineAlgorithmInstance = LineAlgorithm(extraBitmap, currentBitmapAction!!)

                    lineAlgorithmInstance.compute(XYPosition(prevX!!, prevY!!), XYPosition(coordinateX, coordinateY))
                }

                extraBitmap.setPixel(coordinateX, coordinateY, Color.BLACK)

                prevX = coordinateX
                prevY = coordinateY

                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                bitmapActionData.add(currentBitmapAction!!)
                currentBitmapAction = null

                prevX = null
                prevY = null
            }
        }

        return true
    }

    fun undo() {
        if (bitmapActionData.size > 0) {
            for ((key, value) in bitmapActionData.last().actionData.distinctBy { it.xyPosition }) {
                extraBitmap.setPixel(key.x, key.y, value)
            }

            invalidate()
            bitmapActionData.removeLast()
        }
    }

    private fun getResizedBitmap(bm: Bitmap, newHeight: Int, newWidth: Int): Bitmap? {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        this.scaleWidth = scaleWidth
        this.scaleHeight = scaleHeight

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)

        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(getResizedBitmap(extraBitmap, this.width, this.width)!!, 0f, 0f, null)
    }

}