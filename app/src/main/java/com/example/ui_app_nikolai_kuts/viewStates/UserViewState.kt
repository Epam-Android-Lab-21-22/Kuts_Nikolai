package com.example.ui_app_nikolai_kuts.viewStates

import com.example.ui_app_nikolai_kuts.domain.entities.pojo.User

sealed class UserViewState {

    object UserLoadingState : UserViewState()
    class UserLoadedState(val users: List<User>) : UserViewState()
    object UserNoItemState : UserViewState()
}

sealed class UserEvent {
    object FetchUsers : UserEvent()
    object DeleteUser : UserEvent()
}