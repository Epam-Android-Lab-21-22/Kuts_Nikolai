package com.example.ui_app_nikolai_kuts.data.common

import android.util.Log
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor

class ItemColorParser(private val itemColors: String) {


    companion object {
        private const val PREFIX = "["
        private const val SUFFIX = "]"
        private const val OLD_REPLACEMENT_VALUE = "), "
        private const val NEW_REPLACEMENT_VALUE = ")>, "
        private const val ITEM_COLOR_SPLIT_DELIMITER = ">, "

        private const val PARAMETER_DELIMITER = "("
        private const val PARAMETER_REMOVE_SUFFIX = ")"
        private const val PARAMETER_SPLIT_DELIMITER = ", "
        private const val PARAMETER_VALUE_DELIMITER = "="

    }

    fun parseItemColors(): List<ItemColor> {
        Log.i("app_log", "parseItemColors: $itemColors")
        return itemColors.removeSurrounding(prefix = PREFIX, suffix = SUFFIX)
            .replace(oldValue = OLD_REPLACEMENT_VALUE, newValue = NEW_REPLACEMENT_VALUE)
            .split(ITEM_COLOR_SPLIT_DELIMITER)
            .toList()
            .mapNotNull { itemColor: String -> parseItemColor(itemColor) }
    }


    private fun parseItemColor(itemColor: String): ItemColor? {
        return createItemColor(parameters = getParametersAsList(itemColor))
    }

    private fun getParametersAsList(itemColor: String): List<String> {
        return itemColor.substringAfter(PARAMETER_DELIMITER)
            .removeSuffix(PARAMETER_REMOVE_SUFFIX)
            .split(PARAMETER_SPLIT_DELIMITER)
            .toList()
            .map { it.substringAfter(PARAMETER_VALUE_DELIMITER) }
    }

    private fun createItemColor(parameters: List<String>): ItemColor? {
       return ItemColor(
           alpha = parameters[0].toInt(),
           red = parameters[1].toInt(),
           green = parameters[2].toInt(),
           blue = parameters[3].toInt())
    }
}