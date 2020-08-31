package com.lpfun.transport.common.validator.profile

import com.lpfun.transport.common.validator.HandleError
import com.lpfun.transport.common.validator.IValidator
import com.lpfun.transport.common.validator.ValidationResult

class ProfileFieldLengthValidator(private val maxLength: Int) : IValidator<String?> {
    override fun validate(arg: String?) = ValidationResult(
            if (arg != null && arg.length > maxLength) HandleError(
                    field = arg,
                    message = "Ошибка валидации",
                    level = HandleError.ErrorLevel.ERROR,
                    description = "Количество символов больше $maxLength"
            ) else null
    )
}