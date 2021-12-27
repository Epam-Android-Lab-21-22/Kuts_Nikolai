package com.example.ui_app_nikolai_kuts

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat

class ColorAssistant {

    private val alphaRange = 90..150
    private val red = 0..225
    private val green = 0..225
    private val blue = 0..225

    fun getRandomColor(): Int = Color.argb(
        alphaRange.random(),
        red.random(),
        green.random(),
        blue.random()
    )

    fun setBackgroundTint(view: View, color: Int) {
        ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(color))
    }
}