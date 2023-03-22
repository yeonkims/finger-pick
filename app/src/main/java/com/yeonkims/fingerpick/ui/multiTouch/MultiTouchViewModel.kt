package com.yeonkims.fingerpick.ui.multiTouch

import android.view.MotionEvent
import com.yeonkims.fingerpick.data.Circle

class MultiTouchViewModel {
    private val circles = mutableMapOf<Int, Circle>()

    fun onTouchEvent(event: MotionEvent) {
        val index = event.actionIndex
        val id = event.getPointerId(index)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                updateCircle(id, event.getX(index), event.getY(index))
            }
            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                removeCircle(id)
            }
        }
    }

    fun getCircles(): List<Circle> {
        return circles.values.toList()
    }

    private fun updateCircle(id: Int, x: Float, y: Float) {
        circles[id] = Circle(x, y)
    }

    private fun removeCircle(id: Int) {
        circles.remove(id)
    }

}