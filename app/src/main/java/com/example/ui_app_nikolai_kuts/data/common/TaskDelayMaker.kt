package com.example.ui_app_nikolai_kuts.data.common

import kotlinx.coroutines.*

class TaskDelayMaker {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun perform(latency: Long, task: () -> Unit, onFinish: () -> Unit) {
        scope.launch {
            task()
            delay(latency)
            withContext(Dispatchers.Main) { onFinish() }
        }
    }
}