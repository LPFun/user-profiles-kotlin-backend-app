package com.lpfun.transport.multiplatform.profile

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class KmpProfileResponse(
        @Transient open val status: KmpProfileResponseStatus? = null,
        @Transient open val errors: List<KmpProfileError>? = null,
)