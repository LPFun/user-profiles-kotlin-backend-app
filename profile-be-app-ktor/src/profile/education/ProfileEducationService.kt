package com.lpfun.profile.education

import com.lpfun.backend.common.model.profile.base.ProfileContextStatus
import com.lpfun.backend.common.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.model.profile.education.EducationModel
import com.lpfun.backend.common.model.profile.education.ProfileEducation
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import java.util.*

class ProfileEducationService {
    private var profileEducationModel = ProfileEducation(
            profileId = "123",
            mainEducation = mutableListOf(
                    EducationModel(
                            university = "Garvard",
                            department = "IT",
                            specialty = "Programming",
                            yearOfCompletion = "2020"
                    )
            ),
            additionalEducation = mutableListOf(
                    AdditionalEducationModel(
                            nameOfInstitution = "OTUS",
                            courseName = "Kotlin",
                            yearOfCompletion = "2020"
                    )
            )
    )

    fun get(query: KmpProfileEducationGet) = ProfileEducationContext().request {
        setQuery(query)
                .apply {
                    responseProfile = profileEducationModel
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
    }

    fun create(query: KmpProfileEducationCreate) = ProfileEducationContext().request {
        setQuery(query)
                .apply {
                    profileEducationModel = requestProfile.copy(profileId = UUID.randomUUID().toString())
                responseProfile = profileEducationModel
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
    }

    fun update(query: KmpProfileEducationUpdate) = ProfileEducationContext().request {
        setQuery(query)
            .apply {
                requestProfileId = profileEducationModel.profileId
                responseProfile = requestProfile.copy(requestProfileId)
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
    }

    fun delete(query: KmpProfileEducationDelete) = ProfileEducationContext().request {
        setQuery(query)
            .apply {
                responseProfile = ProfileEducation()
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
    }
}