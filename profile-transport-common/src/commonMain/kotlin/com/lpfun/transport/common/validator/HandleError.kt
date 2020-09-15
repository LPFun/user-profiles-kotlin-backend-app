package com.lpfun.transport.common.validator

class HandleError(
    val field: String = "",
    val message: String = "",
    val group: String = "",
    val level: ErrorLevel = ErrorLevel.NONE,
    val description: String = ""
) {
    enum class ErrorLevel(val lvl: Int) {
        NONE(-1),
        WARNING(10),
        ERROR(20)
    }
}