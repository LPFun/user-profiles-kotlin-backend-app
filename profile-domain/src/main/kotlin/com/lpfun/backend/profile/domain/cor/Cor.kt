package com.lpfun.backend.profile.domain.cor

typealias CorMatcherType<T> = suspend T.() -> Boolean
typealias CorHandlerType<T> = suspend T.() -> Unit
typealias CorOnErrorType<T> = suspend T.(Throwable) -> Unit

fun <T> cor(conf: CorProcessor<T>.() -> Unit) = CorProcessor<T>().apply(conf)