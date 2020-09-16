package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.common.model.profile.skills.SpecializationModel
import com.lpfun.transport.multiplatform.profile.skills.*
import com.lpfun.transport.multiplatform.profile.skills.model.KmpProfileSkillsAndTech
import com.lpfun.transport.multiplatform.profile.skills.model.KmpSpecializationModel

fun ProfileSkillsContext.setQuery(get: KmpProfileSkillsAndTechGet) = this.apply {
    requestProfileId = get.profileId ?: ""
}

fun ProfileSkillsContext.setQuery(save: KmpProfileSkillsAndTechSave) = this.apply {
    requestProfile = save.toModel()
}

fun ProfileSkillsContext.setQuery(delete: KmpProfileSkillsAndTechDelete) = this.apply {
    requestProfileId = delete.profileId ?: ""
}

fun ProfileSkillsContext.resultItem() = KmpProfileSkillsAndTechResponse(
    data = responseProfile.toKmp(),
    status = kmpStatus(),
    errors = errors.map { it.toKmp() }
)

fun ProfileSkillsAndTech.toKmp() = KmpProfileSkillsAndTech(
    profileId = profileId,
    specialization = specialization.toKmp(),
    operatingSystems = operatingSystems,
    dataBases = dataBases
)

private fun SpecializationModel.toKmp() = KmpSpecializationModel(
    category = category,
    subCategory = subCategory
)

private fun KmpProfileSkillsAndTechSave.toModel() = ProfileSkillsAndTech(
    profileId = if (this is KmpProfileSkillsAndTechUpdate) id ?: "" else "",
    specialization = specialization.toModel(),
    operatingSystems = operatingSystems.toModel(),
    dataBases = dataBases.toModel()
)

private fun MutableSet<String>?.toModel() = this ?: mutableSetOf()

private fun KmpSpecializationModel?.toModel() = SpecializationModel(
    category = this?.category.toModelString(),
    subCategory = this?.subCategory.toModelString()
)