package com.lpfun.transport.multiplatform.profile.education

import com.lpfun.transport.multiplatform.profile.KmpProfileError
import com.lpfun.transport.multiplatform.profile.KmpProfileResponse
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.education.model.KmpProfileEducation
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducationResponse(
    val data: KmpProfileEducation? = null,
    override val status: KmpProfileResponseStatus? = null,
    override val errors: List<KmpProfileError>? = null,
) : KmpProfileResponse()