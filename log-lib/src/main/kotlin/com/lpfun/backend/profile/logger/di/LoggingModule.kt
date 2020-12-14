package com.lpfun.backend.profile.logger.di

import com.lpfun.backend.profile.logger.IProfileLogger
import com.lpfun.backend.profile.logger.ProfileLogger
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.factory
import org.slf4j.LoggerFactory

val loggingModule = DI.Module("LoggingModule") {
    bind<IProfileLogger>() with factory { c: LoggerParam -> ProfileLogger(LoggerFactory.getLogger(c.c)) }
}

data class LoggerParam(val c: Class<*>)