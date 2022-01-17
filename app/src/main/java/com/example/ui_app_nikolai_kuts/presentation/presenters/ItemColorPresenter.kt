package com.example.ui_app_nikolai_kuts.presentation.presenters

import android.content.SharedPreferences
import com.example.ui_app_nikolai_kuts.data.common.TaskDelayMaker
import com.example.ui_app_nikolai_kuts.data.repository_implementations.ItemColorRepositoryImpl
import com.example.ui_app_nikolai_kuts.domain.repositories.ItemColorRepository
import com.example.ui_app_nikolai_kuts.domain.use_cases.GetItemColors
import com.example.ui_app_nikolai_kuts.domain.use_cases.CreateItemColor
import com.example.ui_app_nikolai_kuts.presentation.view_intefaces.ItemColorView

class ItemColorPresenter(private val view: ItemColorView, preferences: SharedPreferences) {

    private val repository: ItemColorRepository = ItemColorRepositoryImpl(preferences)
    private val getItemColors = GetItemColors(repository)
    private val createItemColor = CreateItemColor(repository)

    fun loadData() {
        view.onGetData(itemColors = getItemColors())
    }

    fun addItemColor() {
        TaskDelayMaker().perform(
            latency = 1000,
            task =  {createItemColor() },
            onFinish = { view.onDataUpdated(itemColors = repository.getItemColors()) }
        )
    }
}