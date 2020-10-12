package com.lpfun.backend.profile.domain.cor

@CorDslMarker
class CorProcessor<T>(
    var matcher: CorMatcherType<T> = { true },
    var handlers: MutableList<IExec<T>> = mutableListOf(),
    var onError: CorOnErrorType<T> = { t -> throw t }
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

    fun processor(conf: CorProcessor<T>.() -> Unit) {
        handlers.add(cor(conf))
    }

    fun execute(handler: IExec<T>) {
        handlers.add(handler)
    }

    fun execute(block: CorHandlerType<T>) {
        handlers.add(
            CorHandler(handler = block)
        )
    }

    fun handler(conf: CorHandler<T>.() -> Unit) {
        handlers.add(CorHandler<T>().apply(conf))
    }

    fun condition(conf: CorMatcherType<T>) {
        matcher = conf
    }

    fun error(conf: CorOnErrorType<T>) {
        onError = conf
    }
}