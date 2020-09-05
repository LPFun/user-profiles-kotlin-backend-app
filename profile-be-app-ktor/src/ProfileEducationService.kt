package com.lpfun

import com.lpfun.backend.common.model.profile.*
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import kotlinx.coroutines.runBlocking
import java.util.*

class ProfileEducationService {
    private val profileEducationModel = ProfileEducation(
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
            .resultItem()
    }

    fun create(query: KmpProfileEducationCreate) = runBlocking {
        val context = ProfileContext()
        context
            .setQuery(query)
            .apply {
                responseProfile = (requestProfile as ProfileEducation).copy(id = UUID.randomUUID().toString())
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
            .resultItem()
    }

    fun update(query: KmpProfileEducationUpdate) = runBlocking {
        val context = ProfileContext()
        context
            .setQuery(query)
            .apply {
                profileEducationModel.id = query.profileId ?: ""
                responseProfile = profileEducationModel
            }
            .resultItem()
    }

    fun delete(query: KmpProfileEducationDelete) = runBlocking {
        val context = ProfileContext()
        context
            .setQuery(query)
            .apply {
                responseProfile = ProfileEducation()
                responseProfileStatus = ProfileContextStatus.SUCCESS
            }
            .resultItem()
    }
}