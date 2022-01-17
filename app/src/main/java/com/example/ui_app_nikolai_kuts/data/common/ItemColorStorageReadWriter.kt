package com.example.ui_app_nikolai_kuts.data.common

import android.content.SharedPreferences
import android.util.Log
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor

class ItemColorStorageReadWriter(
    private val preferences: SharedPreferences,
) : StorageReadWriter<ItemColor> {

    companion object {

        private const val KEY = "item_color_key"
        private const val DEFAULT_VALUE = "empty"
    }

    override fun writeIntoStorage(vararg data: ItemColor) {
        Log.i("app_log", "writeIntoStorage: -------- ${data.toList()}")
        preferences.edit()
            .putString(KEY, data.toList().toString())
            .apply()
    }

    override fun readFromStorage(): List<ItemColor> {
        val itemColorsAsString = preferences.getString(KEY, DEFAULT_VALUE) ?: DEFAULT_VALUE
        return if (itemColorsAsString != DEFAULT_VALUE) {
            ItemColorParser(itemColorsAsString).parseItemColors()
        } else {
            emptyList()
        }
    }
}