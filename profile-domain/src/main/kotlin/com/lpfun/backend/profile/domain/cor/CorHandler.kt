package com.lpfun.backend.profile.domain.cor

@CorDslMarker
class CorHandler<T>(
    var matcher: CorMatcherType<T> = { true },
    var handler: CorHandlerType<T> = {},
    var onError: CorOnErrorType<T> = { t -> throw t }
) : IExec<T> {
    override suspend fun exec(ctx: T) {
        try {
            if (matcher(ctx)) handler(ctx)
        } catch (t: Throwable) {
            onError(ctx, t)
        }
    }

    fun condition(conf: CorMatcherType<T>) {
        matcher = conf
    }

    fun exec(conf: CorHandlerType<T>) {
        handler = conf
    }

    fun error(conf: CorOnErrorType<T>) {
        onError = conf
    }
}