package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.AdditionalEducationModel
import com.lpfun.backend.common.model.profile.EducationModel
import com.lpfun.backend.common.model.profile.ProfileEducation
import com.lpfun.transport.multiplatform.profile.KmpProfileResponseStatus
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationResponse
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpProfileEducation

fun setQuery(get: KmpProfileEducationGet) = get.toModel()

fun setQuery(update: KmpProfileEducationUpdate) = update.toModel()

fun ProfileEducation.resultItem() = KmpProfileEducationResponse(
        data = this.toKmp(),
        status = KmpProfileResponseStatus.SUCCESS
)

/*
    Mappers to kmp models
 */

fun ProfileEducation.toKmp() = KmpProfileEducation(
        mainEducation = mainEducation.toKmpEducationList(),
        additionalEducation = additionalEducation.toKmpAdditionalEducation()
)

private fun MutableList<AdditionalEducationModel>.toKmpAdditionalEducation() = this.map {
    KmpAdditionalEducationModel(
            nameOfInstitution = it.nameOfInstitution,
            courseName = it.courseName,
            yearOfCompletion = it.yearOfCompletion
    )
}.toMutableList()

private fun MutableList<EducationModel>.toKmpEducationList(): MutableList<KmpEducationModel>? = this.map {
    KmpEducationModel(
            university = it.university,
            department = it.department,
            specialty = it.specialty,
            yearOfCompletion = it.yearOfCompletion
    )
}.toMutableList()

/*
    Mappers to common models
 */

fun KmpProfileEducationGet.toModel() = ProfileEducation(
        profileId = profileId ?: ""
)

fun KmpProfileEducationUpdate.toModel() = ProfileEducation(
        profileId = id ?: "",
        mainEducation = mainEducation.toModelEducationList(),
        additionalEducation = additionalEducation.toAdditionalEducationList(),
)

private fun MutableList<KmpAdditionalEducationModel>?.toAdditionalEducationList(): MutableList<AdditionalEducationModel> {
    return this?.map {
        AdditionalEducationModel(
                nameOfInstitution = it.nameOfInstitution ?: "",
                courseName = it.courseName ?: "",
                yearOfCompletion = it.yearOfCompletion ?: ""
        )
    }?.toMutableList() ?: mutableListOf()
}

private fun MutableList<KmpEducationModel>?.toModelEducationList(): MutableList<EducationModel> {
    return this?.map {
        EducationModel(
                it.university ?: "",
                it.department ?: "",
                it.specialty ?: "",
                it.yearOfCompletion ?: ""
        )
    }?.toMutableList() ?: mutableListOf()
}