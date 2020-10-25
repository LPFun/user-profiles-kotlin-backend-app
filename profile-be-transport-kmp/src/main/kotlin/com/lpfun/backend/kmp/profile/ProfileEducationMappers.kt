package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubDelete
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.model.profile.education.EducationModel
import com.lpfun.backend.common.model.profile.education.ProfileEducation
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.transport.multiplatform.profile.education.*
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpProfileEducation

fun ProfileEducationContext.setQuery(get: KmpProfileEducationGet) = this.apply {
    requestProfileId = get.profileId ?: ""
    stubCaseGet = when (get.debug?.stub) {
        KmpProfileEducationGet.StubCase.RUNNING -> ProfileStubGet.SUCCESS
        else -> ProfileStubGet.NONE
    }
}

fun ProfileEducationContext.setQuery(save: KmpProfileEducationSave) = this.apply {
    requestProfile = save.toModel()
    when (save) {
        is KmpProfileEducationCreate -> stubCaseCreate = when (save.debug?.stub) {
            KmpProfileEducationCreate.StubCase.RUNNING -> ProfileStubCreate.SUCCESS
            else -> ProfileStubCreate.NONE
        }
        is KmpProfileEducationUpdate -> stubCaseUpdate = when (save.debug?.stub) {
            KmpProfileEducationUpdate.StubCase.RUNNING -> ProfileStubUpdate.SUCCESS
            else -> ProfileStubUpdate.NONE
        }
    }
}

fun ProfileEducationContext.setQuery(del: KmpProfileEducationDelete) = this.apply {
    requestProfileId = del.profileId ?: ""
    stubCaseDelete = when (del.debug?.stub) {
        KmpProfileEducationDelete.StubCase.RUNNING -> ProfileStubDelete.SUCCESS
        else -> ProfileStubDelete.NONE
    }
}

fun ProfileEducationContext.resultItem() = KmpProfileEducationResponse(
    data = responseProfile.toKmp(),
    status = kmpStatus(),
    errors = errors.map { it.toKmp() }
)

/*
    Mappers to kmp models
 */
fun ProfileEducation.toKmp() = KmpProfileEducation(
    profileId = profileId,
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

fun KmpProfileEducationSave.toModel() = ProfileEducation(
    profileId = if (this is KmpProfileEducationUpdate) profileId ?: "" else "",
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
            university = it.university ?: "",
            department = it.department ?: "",
            specialty = it.specialty ?: "",
            yearOfCompletion = it.yearOfCompletion ?: ""
        )
    }?.toMutableList() ?: mutableListOf()
}