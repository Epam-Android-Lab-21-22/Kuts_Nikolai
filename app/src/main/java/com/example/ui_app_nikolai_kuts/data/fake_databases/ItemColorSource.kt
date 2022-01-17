package com.example.ui_app_nikolai_kuts.data.fake_databases

import android.content.SharedPreferences
import com.example.ui_app_nikolai_kuts.data.common.ItemColorGenerator
import com.example.ui_app_nikolai_kuts.data.common.ItemColorStorageReadWriter
import com.example.ui_app_nikolai_kuts.data.common.StorageReadWriter
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor
import com.example.ui_app_nikolai_kuts.domain.update

class ItemColorSource private constructor(preferences: SharedPreferences) {

    companion object {

        private const val FIRST_RUNNING_KEY = "first_item_color_source_running_key"
        private var source: ItemColorSource? = null

        fun getInstance(preferences: SharedPreferences): ItemColorSource {
            return source ?: synchronized(this) {
                val instance = ItemColorSource(preferences)
                source = instance
                instance
            }
        }
    }

    private val storageReadWriter: StorageReadWriter<ItemColor> =
        ItemColorStorageReadWriter(preferences)
    private val itemColorGenerator = ItemColorGenerator()


    private val _data: MutableList<ItemColor> = mutableListOf()
    val data: List<ItemColor> = _data

    init {
//        saveInitialData()
        if (preferences.getBoolean(FIRST_RUNNING_KEY, true)) {
            saveInitialData()
            preferences.edit().putBoolean(FIRST_RUNNING_KEY, false).apply()
        }

        _data.update(storageReadWriter.readFromStorage())
    }

    fun createItemColor() {
        _data.add(itemColorGenerator.generateRandomItemColor())
        storageReadWriter.writeIntoStorage(*_data.toTypedArray())
    }

    private fun saveInitialData() {
        val initData = itemColorGenerator.generateItemColors(quantity = 5).toMutableList()
        storageReadWriter.writeIntoStorage(data = initData.toTypedArray())
    }
}