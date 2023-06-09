package com.yeonkims.fingerpick.ui.multiTouch

import android.content.Context
import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.yeonkims.fingerpick.data.Circle
import com.yeonkims.fingerpick.util.ResourceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.media.SoundPool
import com.yeonkims.fingerpick.util.SoundManager

class MultiTouchViewModel(val context: Context) : ViewModel() {
    private val circles = mutableMapOf<Int, Circle>()
    private var lastCircleUpdatedTime = 0L
    var isPicked = false

    private var lottieCompositions = ResourceManager.lottieResourceIds
    val lottieDrawables = mutableListOf<LottieDrawable>()

    init {
        initLottieDrawables()
    }

    fun onTouchEvent(event: MotionEvent) {
        val index = event.actionIndex
        val id = event.getPointerId(index)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                if(!isPicked) {
                    updateCircle(id, event.getX(index), event.getY(index))
                    SoundManager.playSound(context, ResourceManager.touchSound)
                    lastCircleUpdatedTime = System.currentTimeMillis()
                } else {
                    if (System.currentTimeMillis() - lastCircleUpdatedTime >= 1000)
                        reset()
                    SoundManager.playSound(context, ResourceManager.clearSound)
                }
            }
            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                if(!isPicked) {
                    removeCircle(id)
                    lastCircleUpdatedTime = System.currentTimeMillis()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val pointerId = event.getPointerId(i)
                    circles[pointerId]?.let { circle ->
                        circle.x = event.getX(i)
                        circle.y = event.getY(i)
                    }
                }
            }
        }

        viewModelScope.launch {
            delay(2000)
            if (System.currentTimeMillis() - lastCircleUpdatedTime >= 2000 && !isPicked && circles.size > 1) {
                SoundManager.playSound(context, ResourceManager.doneSound)
                removeRandomCircles()
                lastCircleUpdatedTime = System.currentTimeMillis()
                isPicked = true
            }
        }
    }

    fun getCircles(): List<Circle> {
        return circles.values.toList()
    }

    private fun updateCircle(id: Int, x: Float, y: Float) {
        circles[id] = Circle(id, x, y)
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

    private fun initLottieDrawables() {
        lottieCompositions.forEach {
            val lottieDrawable = LottieDrawable().apply {
                composition = LottieCompositionFactory.fromRawResSync(context, it).value
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
            lottieDrawables.add(lottieDrawable)
        }
    }

    private fun reset() {
        lastCircleUpdatedTime = 0L
        isPicked = false
        circles.clear()
        lottieCompositions = ResourceManager.lottieResourceIds
        lottieDrawables.clear()
        initLottieDrawables()
    }

}