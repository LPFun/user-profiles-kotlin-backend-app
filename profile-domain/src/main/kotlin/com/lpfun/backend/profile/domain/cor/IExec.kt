package com.lpfun.backend.profile.domain.cor

interface IExec<T> {
    suspend fun exec(ctx: T)
}