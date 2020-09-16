package com.lpfun.profile.skillsandtech

import com.lpfun.backend.common.model.profile.ProfileContextStatus
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.common.model.profile.skills.SpecializationModel
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechCreate
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechDelete
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechGet
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechUpdate
import java.util.*

class ProfileSkillsAndTechService {
    private var skillsAndTechProfile = ProfileSkillsAndTech(
            profileId = "111",
            specialization = SpecializationModel(
                    category = "Development",
                    subCategory = "Mobile developer"
            ),
            operatingSystems = mutableSetOf("Android"),
            dataBases = mutableSetOf("Mysql")
    )

    fun get(query: KmpProfileSkillsAndTechGet) = ProfileSkillsContext().request {
        if (query.profileId == skillsAndTechProfile.profileId) {
            setQuery(query)
                    .apply {
                        responseProfile = skillsAndTechProfile
                        responseProfileStatus = ProfileContextStatus.SUCCESS
                    }
        } else throw IllegalArgumentException()
    }

    fun create(createRequestBody: KmpProfileSkillsAndTechCreate?) =
            ProfileSkillsContext().request {
                createRequestBody?.let {
                    setQuery(it)
                            .apply {
                                skillsAndTechProfile = (requestProfile as ProfileSkillsAndTech).apply {
                                    profileId = UUID.randomUUID().toString()
                                }
                                responseProfile = skillsAndTechProfile
                                responseProfileStatus = ProfileContextStatus.SUCCESS
                            }
                } ?: throw IllegalArgumentException()
            }

    fun update(requestUpdate: KmpProfileSkillsAndTechUpdate?) =
            ProfileSkillsContext().request {
                setQuery(requestUpdate!!)
                        .apply {
                            skillsAndTechProfile = (requestProfile as ProfileSkillsAndTech).copy(skillsAndTechProfile.profileId)
                            responseProfile = skillsAndTechProfile
                            responseProfileStatus = ProfileContextStatus.SUCCESS
                        }
            }

    fun delete(requestDelete: KmpProfileSkillsAndTechDelete?) =
            ProfileSkillsContext().request {
                setQuery(requestDelete!!)
                        .apply {
                            skillsAndTechProfile = ProfileSkillsAndTech()
                            responseProfile = skillsAndTechProfile
                            responseProfileStatus = ProfileContextStatus.SUCCESS
                        }
            }
}