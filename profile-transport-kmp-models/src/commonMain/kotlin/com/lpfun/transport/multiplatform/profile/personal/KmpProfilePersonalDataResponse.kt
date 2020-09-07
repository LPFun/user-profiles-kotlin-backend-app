package com.lpfun.transport.multiplatform.profile.personal

import com.lpfun.transport.multiplatform.profile.KmpProfileError
import com.lpfun.transport.multiplatform.profile.KmpProfileResponse
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.personal.model.KmpProfilePersonalData
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfilePersonalDataResponse(
        val data: KmpProfilePersonalData? = null,
        override val status: KmpProfileResponseStatus? = null,
        override val errors: List<KmpProfileError>? = null
) : KmpProfileResponse(
        status = status,
        errors = errors
)