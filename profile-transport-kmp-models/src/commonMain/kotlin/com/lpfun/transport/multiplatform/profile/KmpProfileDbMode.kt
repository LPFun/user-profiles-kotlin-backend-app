package com.lpfun.transport.multiplatform.profile

import kotlinx.serialization.Serializable

@Serializable
enum class KmpProfileDbMode {
    PROD,
    TEST
}