package com.example.ui_app_nikolai_kuts.data.common

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor

class ItemColorGenerator {

    private val alphaRange = 90..150
    private val redRange = 0..225
    private val greenRange = 0..225
    private val blueRange = 0..225

    fun generateItemColors(quantity: Int): List<ItemColor> {
        return (0..quantity).map { generateRandomItemColor() }
    }

    fun generateRandomItemColor(): ItemColor {
        return ItemColor(
            alpha = alphaRange.random(),
            red = redRange.random(),
            green = greenRange.random(),
            blue = blueRange.random()
        )
    }
}