package com.lpfun.backend.common.model.profile

data class ProfileEducation(
        override var id: String = "",
        var mainEducation: MutableList<EducationModel> = mutableListOf(),
        var additionalEducation: MutableList<AdditionalEducationModel> = mutableListOf()
) : ProfileBase()

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