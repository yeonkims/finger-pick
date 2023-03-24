package com.yeonkims.fingerpick.ui.multiTouch

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MultiTouch(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val viewModel = MultiTouchViewModel(context)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewModel.onTouchEvent(event)
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val lottieDrawables = viewModel.lottieDrawables
        val height = lottieDrawables[0].intrinsicHeight
        val width = lottieDrawables[0].intrinsicWidth

        val scaleFactor = if (viewModel.isPicked) 8f else 5f

        viewModel.getCircles().forEach { circle ->
            canvas!!.save()
            val xPos = circle.x
            val yPos = circle.y
            canvas.scale(scaleFactor, scaleFactor, xPos, yPos)
            canvas.translate(xPos - width / 2, yPos - height / 2)
            invalidate()
            lottieDrawables[circle.id % lottieDrawables.size].draw(canvas)
            canvas.restore()
        }
    }
}