package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubCreate
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubGet
import com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubUpdate
import com.lpfun.backend.common.profile.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.profile.model.profile.education.EducationModel
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.transport.multiplatform.profile.education.*
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpProfileEducation

fun ProfileEducationContext.setQuery(get: KmpProfileEducationGet) = this.apply {
    requestProfile.profileId = get.profileId ?: ""
    requestProfileId = get.profileId ?: ""
    stubCaseGet = when (get.debug?.stub) {
        KmpProfileEducationGet.StubCase.RUNNING -> ProfileStubGet.SUCCESS
        else -> ProfileStubGet.NONE
    }
    workMode = get.debug?.db.toModel()
}

fun ProfileEducationContext.setQuery(save: KmpProfileEducationSave) = this.apply {
    requestProfile = save.toModel()
    when (save) {
        is KmpProfileEducationCreate -> {
            workMode = save.debug?.db.toModel()
            stubCaseCreate = when (save.debug?.stub) {
                KmpProfileEducationCreate.StubCase.RUNNING -> ProfileStubCreate.SUCCESS
                else -> ProfileStubCreate.NONE
            }
        }
        is KmpProfileEducationUpdate -> {
            workMode = save.debug?.db.toModel()
            stubCaseUpdate = when (save.debug?.stub) {
                KmpProfileEducationUpdate.StubCase.RUNNING -> ProfileStubUpdate.SUCCESS
                else -> ProfileStubUpdate.NONE
            }
        }
    }
}

fun ProfileEducationContext.setQuery(del: KmpProfileEducationDelete) = this.apply {
    requestProfile.profileId = del.profileId ?: ""
    requestProfileId = del.profileId ?: ""
    stubCaseDelete = when (del.debug?.stub) {
        KmpProfileEducationDelete.StubCase.RUNNING -> com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete.SUCCESS
        else -> com.lpfun.backend.common.profile.model.profile.base.stub.ProfileStubDelete.NONE
    }
    workMode = del.debug?.db.toModel()
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
        id = it.id,
        nameOfInstitution = it.nameOfInstitution,
        courseName = it.courseName,
        yearOfCompletion = it.yearOfCompletion
    )
}.toMutableList()

private fun MutableList<EducationModel>.toKmpEducationList(): MutableList<KmpEducationModel>? = this.map {
    KmpEducationModel(
        id = it.id,
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
            id = it.id ?: "",
            nameOfInstitution = it.nameOfInstitution ?: "",
            courseName = it.courseName ?: "",
            yearOfCompletion = it.yearOfCompletion ?: ""
        )
    }?.toMutableList() ?: mutableListOf()
}

private fun MutableList<KmpEducationModel>?.toModelEducationList(): MutableList<EducationModel> {
    return this?.map {
        EducationModel(
            id = it.id ?: "",
            university = it.university ?: "",
            department = it.department ?: "",
            specialty = it.specialty ?: "",
            yearOfCompletion = it.yearOfCompletion ?: ""
        )
    }?.toMutableList() ?: mutableListOf()
}
