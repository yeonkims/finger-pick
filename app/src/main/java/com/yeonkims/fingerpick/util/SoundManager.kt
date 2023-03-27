package com.yeonkims.fingerpick.util

import android.content.Context
import android.media.SoundPool

object SoundManager {
    val soundPool: SoundPool = SoundPool.Builder().build()

    fun playSound(context: Context, soundResourceId: Int) {
        val soundId = soundPool.load(context, soundResourceId, 1)
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}