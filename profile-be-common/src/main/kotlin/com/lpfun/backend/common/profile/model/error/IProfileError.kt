package com.lpfun.backend.common.profile.model.error

interface IProfileError {
    val code: String
    val group: Groups
    val field: String
    val level: Levels
    val message: String

    enum class Levels(val weight: Int) {
        ERROR(70),
        WARNING(40),
        INFO(20);

        val isError: Boolean
            get() = weight >= ERROR.weight

        val isWarning: Boolean
            get() = this == WARNING
    }

    enum class Groups(val alias: String) {
        NONE(""),
        VALIDATION("bag-query"),
        INTEGRATION("integration"),
        SERVER("internal-server")
    }
}