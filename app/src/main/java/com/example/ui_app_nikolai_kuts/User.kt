package com.example.ui_app_nikolai_kuts

sealed class User {

    data class RegularPerson(
        val name: String,
        val country: String,
        val city: String,
    ) : User()

    data class Worker(
        val firstName: String,
        val lastname: String,
        val company: String
    ) : User()

    data class Customer(
        val name: String,
        val id: Long,
        val phoneNumber: String
    ) : User()
}