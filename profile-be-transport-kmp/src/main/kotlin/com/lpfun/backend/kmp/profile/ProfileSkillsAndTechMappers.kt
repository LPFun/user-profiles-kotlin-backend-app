package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.model.profile.ProfileContext
import com.lpfun.backend.common.model.profile.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.SpecializationModel
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechDelete
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechGet
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechSave
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechUpdate
import com.lpfun.transport.multiplatform.profile.skills.model.KmpSpecializationModel

fun ProfileContext.setQuery(get: KmpProfileSkillsAndTechGet) = this.apply {
    requestProfileId = get.profileId ?: ""
}

fun ProfileContext.setQuery(save: KmpProfileSkillsAndTechSave) = this.apply {
    requestProfile = save.toModel()
}

fun ProfileContext.setQuery(delete: KmpProfileSkillsAndTechDelete) = this.apply {
    requestProfileId = delete.profileId ?: ""
}

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