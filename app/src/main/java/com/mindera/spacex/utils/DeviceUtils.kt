package com.mindera.spacex.utils

import android.util.DisplayMetrics
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity

class DeviceUtils {
    companion object {
        fun getScreenSize(ctx: AppCompatActivity): Pair<Int, Int> {
            val displayMetrics = DisplayMetrics()
            ctx.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val width = displayMetrics.widthPixels
            return Pair(width, height)
        }
    }
}