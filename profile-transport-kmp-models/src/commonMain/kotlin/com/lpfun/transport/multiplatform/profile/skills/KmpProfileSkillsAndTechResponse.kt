package com.lpfun.transport.multiplatform.profile.skills

import com.lpfun.transport.multiplatform.profile.KmpProfileError
import com.lpfun.transport.multiplatform.profile.KmpProfileResponse
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.skills.model.KmpProfileSkillsAndTech
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileSkillsAndTechResponse(
        val data: KmpProfileSkillsAndTech? = null,
        override val status: KmpProfileResponseStatus? = null,
        override val errors: List<KmpProfileError>? = null
) : KmpProfileResponse(
        status = status,
        errors = errors
)