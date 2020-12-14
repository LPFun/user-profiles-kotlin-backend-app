package com.lpfun.backend.profile.logger

import org.slf4j.Logger
import org.slf4j.Marker
import org.slf4j.event.Level

interface IProfileLogger {
    suspend fun <T> doLoggingSusp(
        blockId: String,
        requestId: String,
        level: Level = Level.DEBUG,
        marker: Marker? = null,
        block: suspend (Logger) -> T
    ): T

    fun info(format: String, arg1: Pair<String, Any>, arg2: Pair<String, Any>)
}