package com.lpfun.backend.common.model.profile.base

enum class ProfileContextStatus {
    RUNNING,
    FINISHING,
    NONE,
    ERROR,
    SUCCESS;

    val isError
        get() = this == ERROR
}