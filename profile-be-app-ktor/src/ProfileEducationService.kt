package com.lpfun

import com.lpfun.backend.common.model.profile.*
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.kmp.profile.toModel
import com.lpfun.transport.multiplatform.profile.education.*
import kotlinx.coroutines.runBlocking
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

    fun get(query: String) = runBlocking {
        val context = ProfileContext()
        context
            .setQuery(KmpProfileEducationGet(profileId = query))
            .apply {
                responseProfile = profileEducationModel
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
            .resultItem<KmpProfileEducationResponse>()
    }

    fun create(query: KmpProfileEducationCreate) = runBlocking {
        val context = ProfileContext()
        context
            .setQuery(query)
            .apply {
                profileEducationModel = (requestProfile as ProfileEducation).copy(id = UUID.randomUUID().toString())
                responseProfile = profileEducationModel
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
            .resultItem<KmpProfileEducationResponse>()
    }

    fun update(query: KmpProfileEducationUpdate) = runBlocking {
        val context = ProfileContext()
        context
            .setQuery(query)
            .apply {
                requestProfileId = profileEducationModel.id
                responseProfile = query.toModel().apply {
                    id = requestProfileId
                }
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
            .resultItem<KmpProfileEducationResponse>()
    }

    fun delete(query: KmpProfileEducationDelete) = runBlocking {
        val context = ProfileContext()
        context
            .setQuery(query)
            .apply {
                responseProfile = ProfileEducation()
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
            .resultItem<KmpProfileEducationResponse>()
    }
}