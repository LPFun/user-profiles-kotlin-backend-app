package com.lpfun.backend.common.model.profile.education

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
        var university: String = "",
        var department: String = "",
        var specialty: String = "",
        var yearOfCompletion: String = "",
)

data class AdditionalEducationModel(
        var nameOfInstitution: String = "",
        var courseName: String = "",
        var yearOfCompletion: String = "",
)