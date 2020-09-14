package com.lpfun.transport.common.validator

interface IValidator<T> {
    fun validate(arg: T): ValidationResult
}