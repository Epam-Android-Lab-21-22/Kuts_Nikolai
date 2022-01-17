package com.example.ui_app_nikolai_kuts.data.common

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import kotlinx.coroutines.*

class TaskDelayMaker {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun<R> perform(latency: Long, task: () -> Unit, onGetResult: () -> Unit) {
        scope.launch {
            delay(latency)
            withContext(Dispatchers.Main) { onGetResult() }
        }
    }
}