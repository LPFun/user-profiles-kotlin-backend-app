package com.lpfun.transport.common.validator

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ValidatorTest {
    private val someValidator = SomeValidator()

    @Test
    fun positiveValidationTest() {
        val result = someValidator.validate("field")
        assertTrue(result.isOk)
        assertEquals(0, result.errors.size)
    }

    @Test
    fun negativeValidationTest() {
        val result = someValidator.validate("not field")
        assertFalse(result.isOk)
        assertEquals(HandleError.ErrorLevel.ERROR, result.errorLevel)
    }
}

class SomeValidator : IValidator<String> {
    private val field = "field"
    override fun validate(arg: String): ValidationResult = ValidationResult(
            if (arg != field) HandleError(
                    field = field,
                    message = "Not valid",
                    level = HandleError.ErrorLevel.ERROR,
                    description = "Valide"
            ) else null
    )
}


