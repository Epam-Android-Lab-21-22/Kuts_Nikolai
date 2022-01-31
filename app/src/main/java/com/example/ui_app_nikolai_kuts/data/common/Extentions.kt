package com.example.ui_app_nikolai_kuts.data

import com.example.ui_app_nikolai_kuts.data.entities.RoomMessage
import com.example.ui_app_nikolai_kuts.data.entities.SerializableMessage
import com.example.ui_app_nikolai_kuts.domain.entities.Message

fun Message.mapToRoomMessage(): RoomMessage = RoomMessage(text = this.text)

fun Message.mapToSerializableMessage(): SerializableMessage = SerializableMessage(text = this.text)

fun RoomMessage.mapToMessage(): Message = Message(text = this.text)

fun SerializableMessage.mapToMessage(): Message = Message(text = this.text)