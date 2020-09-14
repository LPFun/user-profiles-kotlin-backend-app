package com.lpfun.transport.multiplatform.profile

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class KmpProfile(
        @Transient open var id: String? = null
)