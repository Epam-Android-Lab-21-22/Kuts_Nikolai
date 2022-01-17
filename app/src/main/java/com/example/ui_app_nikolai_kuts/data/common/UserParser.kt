package com.example.ui_app_nikolai_kuts.data.common

import android.util.Log
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User
import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User.*

class UserParser(private val users: String) {

    companion object {
        private const val PREFIX = "["
        private const val SUFFIX = "]"
        private const val UNNECESSARY_TEXT = " (Islamic Republic of)"
        private const val VOID_VALUE = ""
        private const val OLD_REPLACEMENT_VALUE = "), "
        private const val NEW_REPLACEMENT_VALUE = ")>, "
        private const val USER_SPLIT_DELIMITER = ">, "

        private const val PARAMETER_DELIMITER = "("
        private const val PARAMETER_REMOVE_SUFFIX = ")"
        private const val PARAMETER_SPLIT_DELIMITER = ", "
        private const val PARAMETER_VALUE_DELIMITER = "="

        private const val CLASS_NAME_PATTERN = "^\\w+[^(]"
    }

    fun parseUsers(): List<User> {
        Log.i("app_log", "parsUsers -------: $users")
        return users.removeSurrounding(prefix = PREFIX, suffix = SUFFIX)
            .replace(UNNECESSARY_TEXT, VOID_VALUE)
            .replace(oldValue = OLD_REPLACEMENT_VALUE, newValue = NEW_REPLACEMENT_VALUE)
            .split(USER_SPLIT_DELIMITER)
            .toList()
            .mapNotNull { user: String -> parseUser(user) }
    }


    private fun parseUser(user: String): User? {
        val classNameRegex = Regex(CLASS_NAME_PATTERN)
        val matchResult = classNameRegex.find(user)
        val userClassName = matchResult?.value

        return createUser(userClassName, parameters = getParametersAsList(user))
    }

    private fun getParametersAsList(user: String): List<String> {
        return user.substringAfter(PARAMETER_DELIMITER)
            .removeSuffix(PARAMETER_REMOVE_SUFFIX)
            .split(PARAMETER_SPLIT_DELIMITER)
            .toList()
            .map { it.substringAfter(PARAMETER_VALUE_DELIMITER) }
    }

    private fun createUser(className: String?, parameters: List<String>): User? {
        return when (className) {
            RegularPerson::class.simpleName -> {
                RegularPerson(parameters[0], parameters[1], parameters[2])
            }
            Worker::class.simpleName -> {
                Worker(parameters[0], parameters[1], parameters[2])
            }
            Customer::class.simpleName -> {
                Customer(parameters[0], parameters[1].toLong(), parameters[2])
            }
            else -> null
        }
    }
}