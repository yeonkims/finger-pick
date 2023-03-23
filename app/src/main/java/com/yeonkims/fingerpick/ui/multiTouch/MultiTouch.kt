package com.yeonkims.fingerpick.ui.multiTouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.yeonkims.fingerpick.R

class MultiTouch(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val viewModel = MultiTouchViewModel()
    private val lottieComposition = LottieCompositionFactory.fromRawResSync(context, R.raw.circle_green)

    private val lottieDrawable = LottieDrawable().apply {
        composition = lottieComposition.value
        repeatCount = LottieDrawable.INFINITE
        this.playAnimation()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewModel.onTouchEvent(event)
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val height = lottieDrawable.intrinsicHeight
        val width = lottieDrawable.intrinsicWidth
        val scale = 5f

        viewModel.getCircles().forEach { circle ->
            canvas!!.save()
            val xPos = circle.x
            val yPos = circle.y
            canvas.scale(scale, scale, xPos, yPos)
            canvas.translate(xPos - width / 2, yPos - height / 2)
            invalidate()
            lottieDrawable.draw(canvas)
            canvas.restore()
        }
    }
}