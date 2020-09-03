package com.lpfun.transport.common.validator

import com.lpfun.transport.common.validator.profile.ProfileFieldLengthValidator
import com.lpfun.transport.common.validator.profile.ProfileNameValidator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ValidatorsTest {
    @Test
    fun `name success test`() {
        val testData = "Александр"
        val validator = ProfileNameValidator(testData).validate(testData)
        assertTrue(validator.isOk)
    }

    @Test
    fun `name fail test`() {
        val testData = ""
        val validator = ProfileNameValidator(testData).validate(testData)
        assertFalse(validator.isOk)
    }

    @Test
    fun `length success test`() {
        val testStr: StringBuilder = StringBuilder()
        (0..10).forEach {
            testStr.append(it)
        }
        val validatorResult = ProfileFieldLengthValidator(10).validate(testStr.toString())
        assertFalse(validatorResult.isOk)
    }

    @Test
    fun `length fail test`() {
        val testStr: StringBuilder = StringBuilder()
        (0 until 10).forEach {
            testStr.append(it)
        }
        val validatorResult = ProfileFieldLengthValidator(10).validate(testStr.toString())
        assertTrue(validatorResult.isOk)
    }
}