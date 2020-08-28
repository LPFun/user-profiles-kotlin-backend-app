package com.lpfun.transport.multiplatform.profile.education

import com.lpfun.transport.multiplatform.profile.KmpProfileError
import com.lpfun.transport.multiplatform.profile.KmpProfileResponse
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.education.model.KmpProfileEducation

data class KmpProfileEducationResponse(
        val data: KmpProfileEducation,
        override val status: KmpProfileResponseStatus? = null,
        override val errors: List<KmpProfileError>? = null,
): KmpProfileResponse(
        status = status,
        errors = errors,
)