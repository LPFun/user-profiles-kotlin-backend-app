package com.lpfun.transport.common.validator.profile

import com.lpfun.transport.common.validator.IValidator
import com.lpfun.transport.common.validator.ValidationResult

class ProfileNameValidator(
    field: String
) : IValidator<String?> {
    private val validatorStringRegex = ValidatorStringRegex(
        regex = Regex("""^[А-ЯЁа-яё]{2,15}${'$'}"""),
        field = field,
    )

    override fun validate(arg: String?) = ValidationResult(
        *(arg?.let { validatorStringRegex.validate(it).errors.toTypedArray() } ?: emptyArray())
    )
}