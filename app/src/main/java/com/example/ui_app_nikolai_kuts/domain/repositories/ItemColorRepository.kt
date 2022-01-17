package com.example.ui_app_nikolai_kuts.domain.repositories

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.ItemColor

interface ItemColorRepository {

    fun getItemColors(): List<ItemColor>

    fun createItemColor()
}