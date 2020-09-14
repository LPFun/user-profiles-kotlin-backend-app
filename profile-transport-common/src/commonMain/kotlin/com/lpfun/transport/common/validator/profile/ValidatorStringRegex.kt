package com.lpfun.transport.common.validator.profile

import com.lpfun.transport.common.validator.HandleError
import com.lpfun.transport.common.validator.IValidator
import com.lpfun.transport.common.validator.ValidationResult

class ValidatorStringRegex(
    private val regex: Regex,
    private val field: String = "",
    private val message: String = "wrong-symbols",
    private val group: String = "validation",
    private val description: String = "Field contains inappropriate characters",
    private val level: HandleError.ErrorLevel = HandleError.ErrorLevel.ERROR
) : IValidator<String> {
    override fun validate(arg: String) = ValidationResult(
        regex.matches(arg).let {
            if (!it) {
                HandleError(
                    field = field,
                    message = message,
                    group = group,
                    description = description,
                    level = level
                )
            } else null
        }
    )
}