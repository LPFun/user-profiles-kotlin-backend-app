package com.lpfun.backend.common.model.profile

data class ProfileSkillsAndTech(
        var profileId: String = "",
        var specialization: SpecializationModel = SpecializationModel.NONE,
        var operatingSystems: MutableSet<String> = mutableSetOf(),
        var dataBases: MutableSet<String> = mutableSetOf()
): ProfileBase(
        id = profileId
)

data class SpecializationModel(
    var category: String = "",
    var subCategory: String = "",
) {
    companion object{
        val NONE = SpecializationModel()
    }
}