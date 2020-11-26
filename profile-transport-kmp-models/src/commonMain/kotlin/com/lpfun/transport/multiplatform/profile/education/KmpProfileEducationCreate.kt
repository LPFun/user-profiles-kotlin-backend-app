package com.lpfun.transport.multiplatform.profile.education

import com.lpfun.transport.multiplatform.profile.KmpProfileDbMode
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import kotlinx.serialization.Serializable

@Serializable
data class KmpProfileEducationCreate(
    override var mainEducation: MutableList<KmpEducationModel>? = null,
    override var additionalEducation: MutableList<KmpAdditionalEducationModel>? = null,
    var debug: Debug? = null
) : KmpProfileEducationSave(
    mainEducation = mainEducation,
    additionalEducation = additionalEducation
) {
    @Serializable
    class Debug {
        var stub: StubCase? = null
        var db: KmpProfileDbMode? = null
    }

    @Serializable
    enum class StubCase {
        NONE,
        SUCCESS
    }
}