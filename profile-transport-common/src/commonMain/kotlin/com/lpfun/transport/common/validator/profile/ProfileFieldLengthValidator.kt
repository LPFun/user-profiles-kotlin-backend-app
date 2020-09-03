package com.lpfun.transport.common.validator.profile

import com.lpfun.transport.common.validator.HandleError
import com.lpfun.transport.common.validator.IValidator
import com.lpfun.transport.common.validator.ValidationResult

class ProfileFieldLengthValidator(private val maxLength: Int) : IValidator<String?> {
    override fun validate(arg: String?) = ValidationResult(
        if (arg != null && arg.length > maxLength && arg.isNotEmpty()) HandleError(
            field = arg,
            message = "Length validation error",
            level = HandleError.ErrorLevel.ERROR,
            description = "Character count more then $maxLength or 0"
        ) else null
    )
}