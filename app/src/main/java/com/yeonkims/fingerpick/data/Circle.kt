package com.yeonkims.fingerpick.data

import android.graphics.Color
import android.graphics.Paint

data class Circle(
    val x: Float,
    val y: Float,
    val radius: Float = 100f,
    val paint: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }
)