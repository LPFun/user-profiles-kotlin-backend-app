package com.lpfun.backend.profile.logger

import com.lpfun.backend.profile.logger.ProfileLoggerHelper.kv
import net.logstash.logback.argument.StructuredArguments
import org.slf4j.Logger
import org.slf4j.Marker
import org.slf4j.event.Level

class ProfileLogger(val logger: Logger) : IProfileLogger {
    override suspend fun <T> doLoggingSusp(
        blockId: String,
        requestId: String,
        level: Level,
        marker: Marker?,
        block: suspend (Logger) -> T
    ): T {
        logger.log(
            level,
            marker,
            "Entering {}, requestId {}",
            blockId,
            StructuredArguments.keyValue("requestId", requestId)
        )
        return try {
            val res = block(logger)
            logger.log(
                level, marker, "Finished {}, requestId {}", blockId,
                StructuredArguments.keyValue("requestId", requestId)
            )
            res
        } catch (e: Throwable) {
            logger.error(
                marker,
                "FAILED {}, requestId {}",
                blockId,
                StructuredArguments.keyValue("requestId", requestId)
            )
            throw e
        }
    }

    override fun info(format: String, arg1: Pair<String, Any>, arg2: Pair<String, Any>) {
        logger.info(format, kv(arg1.first, arg1.second), kv(arg2.first, arg2.second))
    }
}