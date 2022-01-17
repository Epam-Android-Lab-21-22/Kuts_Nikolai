package com.example.ui_app_nikolai_kuts.presentation.common

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor

class ColorAssistant {

    fun getColorAsInt(itemColor: ItemColor): Int = Color.argb(
        itemColor.alpha,
        itemColor.red,
        itemColor.green,
        itemColor.blue
    )

    fun setBackgroundTint(view: View, color: Int) {
        ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(color))
    }
}