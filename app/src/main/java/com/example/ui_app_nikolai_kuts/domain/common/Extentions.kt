package com.example.ui_app_nikolai_kuts.domain.common

fun<T> MutableList<T>.update(newList: List<T>) {
    this.clear()
    this.addAll(newList)
}