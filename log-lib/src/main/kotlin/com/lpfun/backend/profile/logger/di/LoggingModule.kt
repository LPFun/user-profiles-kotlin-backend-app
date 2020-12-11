package com.lpfun.backend.profile.logger.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val loggingModule = DI.Module("LoggingModule") {
    bind<Logger>() with factory { c: LoggerParam -> LoggerFactory.getLogger(c.c) }
}

data class LoggerParam(val c: Class<*>)