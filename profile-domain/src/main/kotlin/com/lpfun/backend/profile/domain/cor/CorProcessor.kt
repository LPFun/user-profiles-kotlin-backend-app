package com.lpfun.backend.profile.domain.cor

class CorProcessor<T> private constructor(
    private val matcher: CorMatcherType<T>,
    private val handlers: MutableList<IExec<T>>,
    private val onError: CorOnErrorType<T>
) : IExec<T> {

    override suspend fun execute(ctx: T) {
        try {
            if (matcher(ctx)) {
                handlers.forEach {
                    it.execute(ctx)
                }
            }
        } catch (t: Throwable) {
            onError(ctx, t)
        }
    }

    @CorDslMarker
    class Builder<T> {
        private var matcher: CorMatcherType<T> = { true }
        private var handlers: MutableList<IExec<T>> = mutableListOf()
        private var onError: CorOnErrorType<T> = { t -> throw t }

        fun processor(conf: Builder<T>.() -> Unit) {
            handlers.add(cor(conf))
        }

        fun execute(handler: IExec<T>) {
            handlers.add(handler)
        }

        fun execute(block: CorHandlerType<T>) {
            handlers.add(
                corHandler {
                    exec(block)
                }
            )
        }

        fun handler(conf: CorHandler.Builder<T>.() -> Unit) {
            handlers.add(corHandler(conf))
        }

        fun condition(conf: CorMatcherType<T>) {
            matcher = conf
        }

        fun error(conf: CorOnErrorType<T>) {
            onError = conf
        }


        fun build() = CorProcessor(
            matcher = matcher,
            handlers = handlers,
            onError = onError
        )
    }
}