package com.lpfun.profile.skillsandtech

import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.common.model.profile.ProfileContextStatus
import com.lpfun.backend.common.model.profile.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.SpecializationModel
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.skills.*
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

    fun get(paramsList: List<Pair<String, List<String>>>) = ProfileContext().request<KmpProfileSkillsAndTechResponse> {
        val id = paramsList.firstOrNull { it.first == "id" }?.second?.get(0) ?: throw IllegalArgumentException()
        if (id == skillsAndTechProfile.profileId) {
            setQuery(KmpProfileSkillsAndTechGet(id))
                .apply {
                    responseProfile = skillsAndTechProfile
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
        } else throw IllegalArgumentException()
    }

    fun create(createRequestBody: KmpProfileSkillsAndTechCreate?) =
        ProfileContext().request<KmpProfileSkillsAndTechResponse> {
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
        ProfileContext().request<KmpProfileSkillsAndTechResponse> {
            setQuery(requestUpdate!!)
                .apply {
                    skillsAndTechProfile = (requestProfile as ProfileSkillsAndTech).copy(skillsAndTechProfile.profileId)
                    responseProfile = skillsAndTechProfile
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
        }

    fun delete(requestDelete: KmpProfileSkillsAndTechDelete?) =
        ProfileContext().request<KmpProfileSkillsAndTechResponse> {
            setQuery(requestDelete!!)
                .apply {
                    skillsAndTechProfile = ProfileSkillsAndTech()
                    responseProfile = skillsAndTechProfile
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
        }
}