package com.example.ui_app_nikolai_kuts.domain

fun<T> MutableList<T>.update(newList: List<T>) {
    clear()
    addAll(newList)
}