package com.example.ui_app_nikolai_kuts.domain.entities.pojo

import com.example.ui_app_nikolai_kuts.domain.entities.enums.ItemTypes
import com.example.ui_app_nikolai_kuts.domain.ListItem

sealed class User : ListItem {

    data class RegularPerson(
        val name: String,
        val country: String,
        val city: String,
    ) : User() {

        override fun getListItemType(): ItemTypes = ItemTypes.FIRST_ITEM_TYPE
    }

    data class Worker(
        val firstName: String,
        val lastname: String,
        val company: String
    ) : User() {

        override fun getListItemType(): ItemTypes = ItemTypes.SECOND_ITEM_TYPE
    }

    data class Customer(
        val name: String,
        val id: Long,
        val phoneNumber: String
    ) : User() {

        override fun getListItemType(): ItemTypes = ItemTypes.THIRD_ITEM_TYPE
    }
}