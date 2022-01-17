package com.example.ui_app_nikolai_kuts.data.repository_implementations

import android.content.SharedPreferences
import com.example.ui_app_nikolai_kuts.data.fake_databases.ItemColorSource
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor
import com.example.ui_app_nikolai_kuts.domain.repositories.ItemColorRepository

class ItemColorRepositoryImpl(preferences: SharedPreferences) : ItemColorRepository {

    private val source = ItemColorSource.getInstance(preferences)

    override fun getItemColors(): List<ItemColor>  = source.data

    override fun createItemColor() {
        source.createItemColor()
    }
}