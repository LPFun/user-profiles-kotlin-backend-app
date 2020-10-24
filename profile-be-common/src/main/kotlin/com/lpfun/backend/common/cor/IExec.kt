package com.lpfun.backend.common.cor

interface IExec<T> {
    suspend fun execute(ctx: T)
}