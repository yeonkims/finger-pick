package com.yeonkims.fingerpick.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.yeonkims.fingerpick.data.Circle

class MultiTouch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val circles = mutableMapOf<Int, Circle>()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val index = event.actionIndex
        val id = event.getPointerId(index)

        if (event.actionMasked == MotionEvent.ACTION_DOWN ||
            event.actionMasked == MotionEvent.ACTION_POINTER_DOWN) {
            updateCircle(id, event.getX(index), event.getY(index))
        } else if (event.actionMasked == MotionEvent.ACTION_POINTER_UP ||
            event.actionMasked == MotionEvent.ACTION_UP) {
            removeCircle(id)
        }
        return true
    }

    private fun updateCircle(id: Int, x: Float, y: Float) {
        circles[id] = Circle(x, y)
        invalidate()
    }

    private fun removeCircle(id: Int) {
        circles.remove(id)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        circles.values.forEach { circle ->
            canvas?.drawCircle(circle.x, circle.y, circle.radius, circle.paint)
        }
    }
}

