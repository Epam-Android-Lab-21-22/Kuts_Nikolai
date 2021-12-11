package com.example.ui_app_nikolai_kuts

import android.content.Context

private const val DEFAULT_COLOR_VALUE = 0
private const val START_COLOR_INDEX = 0

class ColorAssistant(private val context: Context) {

    fun getRandomColor(): Int {
        val colors = context.resources.obtainTypedArray(R.array.character_item_background_colors)
        val colorIndices = (START_COLOR_INDEX until colors.length())
        val color = colors.getColor(colorIndices.random(), DEFAULT_COLOR_VALUE)
        colors.recycle()
        return color
    }
}