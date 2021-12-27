package com.example.ui_app_nikolai_kuts

import com.github.javafaker.Faker

class UserGenerator {

    private val faker = Faker.instance()
    private val randomizingRange = 0..2

    fun generateUsers(userQuantity: Int): List<User> {
        faker.address().country()
        return (0..userQuantity).map {
            generateRandomTypeUser()
        }
    }

    private fun generateRandomTypeUser(): User {
        return when (randomizingRange.random()) {
            0 -> generateRandomRegularPerson()
            1 -> generateRandomWorker()
            else -> generateRandomCustomer()
        }
    }

    private fun generateRandomRegularPerson(): User {
        return User.RegularPerson(
            name = faker.name().name(),
            country = faker.country().name(),
            city = faker.address().cityName()
        )
    }

    private fun generateRandomWorker(): User {
        return User.Worker(
            firstName = faker.name().firstName(),
            lastname = faker.name().lastName(),
            company = faker.company().name()
        )
    }

    private fun generateRandomCustomer(): User {
        return User.Customer(
            name = faker.name().firstName(),
            id = faker.number().randomNumber(),
            phoneNumber = faker.phoneNumber().phoneNumber()
        )
    }
}