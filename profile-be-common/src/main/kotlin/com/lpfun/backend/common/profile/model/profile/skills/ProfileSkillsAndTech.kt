package com.lpfun.backend.common.profile.model.profile.skills

data class ProfileSkillsAndTech(
    var profileId: String = "",
    var specialization: SpecializationModel = SpecializationModel.NONE,
    var operatingSystems: MutableSet<OperatingSystemModel> = mutableSetOf(),
    var dataBases: MutableSet<DataBaseModel> = mutableSetOf()
) {
    companion object {
        val NONE = ProfileSkillsAndTech()
    }
}

data class OperatingSystemModel(
    var id: String = "",
    var operatingSystem: String = ""
)

data class DataBaseModel(
    var id: String = "",
    var dataBase: String = ""
)

data class SpecializationModel(
    var category: String = "",
    var subCategory: String = "",
) {
    companion object {
        val NONE = SpecializationModel()
    }
}