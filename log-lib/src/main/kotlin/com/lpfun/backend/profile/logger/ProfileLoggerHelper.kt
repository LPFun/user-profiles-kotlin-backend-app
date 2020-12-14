package com.lpfun.backend.profile.logger

import net.logstash.logback.argument.StructuredArguments.keyValue

object ProfileLoggerHelper {
    fun kv(key: String, value: Any) = keyValue(key, value)
}