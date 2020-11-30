package com.lpfun.backend.common

enum class WorkMode {
    PROD,
    TEST;

    companion object {
        val DEFAULT = TEST
    }
}