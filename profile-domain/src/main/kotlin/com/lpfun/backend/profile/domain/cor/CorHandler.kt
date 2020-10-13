package com.lpfun.backend.profile.domain.cor

class CorHandler<T> private constructor(
    private val matcher: CorMatcherType<T> = { true },
    private val handler: CorHandlerType<T> = {},
    private val onError: CorOnErrorType<T> = { t -> throw t }
) : IExec<T> {
    override suspend fun execute(ctx: T) {
        try {
            if (matcher(ctx)) handler(ctx)
        } catch (t: Throwable) {
            onError(ctx, t)
        }
    }

    @CorDslMarker
    class Builder<T> {
        private var matcher: CorMatcherType<T> = { true }
        private var handler: CorHandlerType<T> = {}
        private var onError: CorOnErrorType<T> = { t -> throw t }

        fun condition(conf: CorMatcherType<T>) {
            matcher = conf
        }

        fun exec(conf: CorHandlerType<T>) {
            handler = conf
        }

        fun error(conf: CorOnErrorType<T>) {
            onError = conf
        }

        fun build() = CorHandler<T>(
            matcher = matcher,
            handler = handler,
            onError = onError
        )
    }
}