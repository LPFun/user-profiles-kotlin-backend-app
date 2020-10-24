package com.lpfun.backend.common.cor

typealias CorMatcherType<T> = suspend T.() -> Boolean
typealias CorHandlerType<T> = suspend T.() -> Unit
typealias CorOnErrorType<T> = suspend T.(Throwable) -> Unit

fun <T> cor(conf: CorProcessor.Builder<T>.() -> Unit) = CorProcessor.Builder<T>().apply(conf).build()

fun <T> corHandler(conf: CorHandler.Builder<T>.() -> Unit) = CorHandler.Builder<T>().apply(conf).build()