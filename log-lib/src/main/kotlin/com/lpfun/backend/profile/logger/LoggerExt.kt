package com.lpfun.backend.profile.logger

import org.slf4j.Logger
import org.slf4j.Marker
import org.slf4j.event.Level

fun Logger.log(level: Level, marker: Marker?, message: String, vararg args: Any) = when (level) {
    Level.TRACE -> trace(marker, message, *args)
    Level.DEBUG -> debug(marker, message, *args)
    Level.INFO -> info(marker, message, *args)
    Level.WARN -> warn(marker, message, *args)
    Level.ERROR -> error(marker, message, *args)
}