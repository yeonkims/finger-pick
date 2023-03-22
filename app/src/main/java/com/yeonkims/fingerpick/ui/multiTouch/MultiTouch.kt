package com.yeonkims.fingerpick.ui.multiTouch

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MultiTouch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val viewModel = MultiTouchViewModel()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewModel.onTouchEvent(event)
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        viewModel.getCircles().forEach { circle ->
            canvas?.drawCircle(circle.x, circle.y, circle.radius, circle.paint)
        }
    }
}

