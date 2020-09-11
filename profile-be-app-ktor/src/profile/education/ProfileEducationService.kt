package com.lpfun.profile.education

import com.lpfun.backend.common.model.profile.*
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.education.*
import java.util.*

class ProfileEducationService {
    private var profileEducationModel = ProfileEducation(
        id = "123",
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

    fun get(paramsList: List<Pair<String, List<String>>>) = ProfileContext().request<KmpProfileEducationResponse> {
        setQuery(KmpProfileEducationGet(profileId = paramsList.first { it.first == "id" }.second.get(0)))
            .apply {
                responseProfile = profileEducationModel
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
    }

    fun create(query: KmpProfileEducationCreate) = ProfileContext().request<KmpProfileEducationResponse> {
        setQuery(query)
            .apply {
                profileEducationModel = (requestProfile as ProfileEducation).copy(id = UUID.randomUUID().toString())
                responseProfile = profileEducationModel
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
    }

    fun update(query: KmpProfileEducationUpdate) = ProfileContext().request<KmpProfileEducationResponse> {

        setQuery(query)
            .apply {
                responseProfile = requestProfile
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }

    }

    fun delete(query: KmpProfileEducationDelete) = ProfileContext().request<KmpProfileEducationResponse> {
        setQuery(query)
            .apply {
                responseProfile = ProfileEducation()
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
    }
}