package com.lpfun.profile.education

import com.lpfun.backend.common.model.error.InternalServerError
import com.lpfun.backend.common.model.profile.*
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.kmp.profile.setQuery
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

    fun get(query: String) = ProfileContext().run {
        try {
            setQuery(KmpProfileEducationGet(profileId = query))
                .apply {
                    responseProfile = profileEducationModel
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem<KmpProfileEducationResponse>()
    }

    fun create(query: KmpProfileEducationCreate) = ProfileContext().run {
        try {
            setQuery(query)
                .apply {
                    profileEducationModel = (requestProfile as ProfileEducation).copy(id = UUID.randomUUID().toString())
                    responseProfile = profileEducationModel
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem<KmpProfileEducationResponse>()
    }

    fun update(query: KmpProfileEducationUpdate) = ProfileContext().run {
        try {
            setQuery(query)
                .apply {
                    responseProfile = requestProfile
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem<KmpProfileEducationResponse>()
    }

    fun delete(query: KmpProfileEducationDelete) = ProfileContext().run {
        try {
            setQuery(query)
                .apply {
                    responseProfile = ProfileEducation()
                    responseProfileStatus = ProfileContextStatus.SUCCESS
                }
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem<KmpProfileEducationResponse>()
    }
}