package com.yeonkims.fingerpick.ui.multiTouch

import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeonkims.fingerpick.data.Circle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MultiTouchViewModel : ViewModel() {
    private val circles = mutableMapOf<Int, Circle>()
    private var lastCircleUpdatedTime = 0L
    var isPicked = false

    fun onTouchEvent(event: MotionEvent) {
        val index = event.actionIndex
        val id = event.getPointerId(index)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                updateCircle(id, event.getX(index), event.getY(index))
                lastCircleUpdatedTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                removeCircle(id)
                lastCircleUpdatedTime = System.currentTimeMillis()
            }
        }

        viewModelScope.launch {
            delay(2000)
            if (System.currentTimeMillis() - lastCircleUpdatedTime >= 2000 && !isPicked && circles.isNotEmpty()) {
                removeRandomCircles()
                isPicked = true
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

    private fun removeRandomCircles() {
        val keepCircleId = circles.keys.random()
        val keepCircle = circles[keepCircleId]
        circles.clear()
        circles[keepCircleId] = keepCircle!!
    }

}