package com.lpfun.backend.common.profile.model.profile.education

data class ProfileEducation(
    var profileId: String = "",
    var mainEducation: MutableList<EducationModel> = mutableListOf(),
    var additionalEducation: MutableList<AdditionalEducationModel> = mutableListOf()
) {
    companion object {
        val NONE = ProfileEducation()
    }
}

data class EducationModel(
    var id: String = "",
    var university: String = "",
    var department: String = "",
    var specialty: String = "",
    var yearOfCompletion: String = "",
)

data class AdditionalEducationModel(
    var id: String = "",
    var nameOfInstitution: String = "",
    var courseName: String = "",
    var yearOfCompletion: String = "",
)