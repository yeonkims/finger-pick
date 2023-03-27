package com.yeonkims.fingerpick.util

import com.yeonkims.fingerpick.R

object ResourceManager {
    const val touchSound = R.raw.sound_touch
    const val doneSound = R.raw.sound_done
    const val clearSound = R.raw.sound_clear

    val lottieResourceIds = listOf(
        R.raw.circle_green,
        R.raw.circle_bleen,
        R.raw.circle_deepblue,
        R.raw.circle_grey,
        R.raw.circle_orange,
        R.raw.circle_pink,
        R.raw.circle_purple,
        R.raw.circle_red,
        R.raw.circle_sky,
        R.raw.circle_yellow
    )
    get() {
        return field.shuffled()
    }
}