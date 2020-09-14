package com.lpfun.transport.common.validator

data class ValidationResult(
        val errors: List<HandleError> = emptyList()
) {
    constructor(vararg error: HandleError?) : this(errors = error.filterNotNull().toList())

    val isOk: Boolean
        get() = errors.isEmpty()

    val errorLevel: HandleError.ErrorLevel
        get() = errors.maxByOrNull { it.level.lvl }?.level ?: HandleError.ErrorLevel.NONE

}