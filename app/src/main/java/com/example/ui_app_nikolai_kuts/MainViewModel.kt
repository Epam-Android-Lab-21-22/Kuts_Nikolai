package com.example.ui_app_nikolai_kuts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val QUANTITY_START_POSITION = 0

class MainViewModel : ViewModel() {

    val enteredText: LiveData<String> get() = _enteredText
    val clickQuantity: LiveData<Int> get() = _clickQuantity
    val characterQuantity: LiveData<Int> get() = _characterQuantity

    private val _enteredText = MutableLiveData<String>()
    private val _clickQuantity = MutableLiveData(QUANTITY_START_POSITION)
    private val _characterQuantity = MutableLiveData(QUANTITY_START_POSITION)

    fun saveText(text: String) {
        _enteredText.value = text
    }

    fun click() {
        _clickQuantity.apply {
            value?.let { clickQuantity -> value = clickQuantity + 1 }
        }
    }

    fun setCharacterQuantity(quantity: Int) {
        _characterQuantity.value = quantity
    }
}