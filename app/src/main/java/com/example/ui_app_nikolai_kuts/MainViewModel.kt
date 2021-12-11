package com.example.ui_app_nikolai_kuts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val QUANTITY_START_POSITION = 0
const val ENTERED_TEXT_KEY = "entered_text"
const val CLICK_QUANTITY = "click_quantity"
const val CHARACTER_QUANTITY = "character_quantity"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val enteredText: LiveData<String> = savedStateHandle.getLiveData(ENTERED_TEXT_KEY)
    val clickQuantity: LiveData<Int> = savedStateHandle.getLiveData(
        CLICK_QUANTITY,
        QUANTITY_START_POSITION
    )
    val characterQuantity: LiveData<Int> = savedStateHandle.getLiveData(
        CHARACTER_QUANTITY,
        QUANTITY_START_POSITION
    )

    fun saveText(text: String) {
        savedStateHandle[ENTERED_TEXT_KEY] = text
    }

    fun click() {
        savedStateHandle[CLICK_QUANTITY] = (clickQuantity.value ?: 0) + 1
    }

    fun setCharacterQuantity(quantity: Int) {
        savedStateHandle[CHARACTER_QUANTITY] = quantity
    }
}