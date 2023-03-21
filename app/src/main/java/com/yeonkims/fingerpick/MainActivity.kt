package com.yeonkims.fingerpick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yeonkims.fingerpick.ui.MultiTouch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MultiTouch(this))
    }
}