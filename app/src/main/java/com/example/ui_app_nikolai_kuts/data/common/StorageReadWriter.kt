package com.example.ui_app_nikolai_kuts.data.common

interface StorageReadWriter<T> {

    fun writeIntoStorage(vararg data: T)

    fun readFromStorage(): List<T>
}