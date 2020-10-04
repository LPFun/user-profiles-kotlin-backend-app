package com.lpfun.backend.common.model.dsl.education

import com.lpfun.backend.common.model.dsl.ProfileDslMarker
import com.lpfun.backend.common.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.model.profile.education.EducationModel
import com.lpfun.backend.common.model.profile.education.ProfileEducation

@ProfileDslMarker
class ProfileEducationDsl(
    var id: String = ""
) {

    var mainEducation = mutableListOf<MainEducationDsl>()
        private set
    var additionalEducation = mutableListOf<AdditionalEducationDsl>()
        private set

    fun mainEducation(conf: MainEducationDsl.() -> Unit) {
        mainEducation.add(MainEducationDsl().apply(conf))
    }

    fun additionalEducation(conf: AdditionalEducationDsl.() -> Unit) {
        additionalEducation.add(AdditionalEducationDsl().apply(conf))
    }
}


fun profileEducation(conf: ProfileEducationDsl.() -> Unit): ProfileEducation = ProfileEducationDsl().apply(conf).run() {
    ProfileEducation(
        profileId = id,
        mainEducation = mainEducation.toModel(),
        additionalEducation = additionalEducation.toModel()
    )
}

@JvmName("toMainModel")
private fun MutableList<MainEducationDsl>.toModel(): MutableList<EducationModel> = run {
    map {
        EducationModel(
            university = it.university,
            department = it.department,
            specialty = it.speciality,
            yearOfCompletion = it.yearOfCompletion
        )
    }.toMutableList()
}

@JvmName("toAdditionalModel")
private fun MutableList<AdditionalEducationDsl>.toModel(): MutableList<AdditionalEducationModel> = run {
    map {
        AdditionalEducationModel(
            nameOfInstitution = it.nameOfInstitution,
            courseName = it.courseName,
            yearOfCompletion = it.yearOfCompletion
        )
    }.toMutableList()
}
