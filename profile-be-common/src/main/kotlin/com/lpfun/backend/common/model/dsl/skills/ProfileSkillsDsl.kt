package com.lpfun.backend.common.model.dsl.skills

import com.lpfun.backend.common.model.dsl.ProfileDslMarker
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsAndTech
import com.lpfun.backend.common.model.profile.skills.SpecializationModel

@ProfileDslMarker
class ProfileSkillsDsl(
    var id: String = ""
) {
    var specialization = SpecializationDsl.EMPTY
        private set
    var operatingSystems = OperatingSystemsDsl.EMPTY
        private set
    var dataBases = DataBasesDsl.EMPTY
        private set

    fun specialization(conf: SpecializationDsl.() -> Unit) {
        specialization = SpecializationDsl().apply(conf)
    }

    fun operatingSystems(conf: OperatingSystemsDsl.() -> Unit) {
        operatingSystems = OperatingSystemsDsl().apply(conf)
    }

    fun dataBases(conf: DataBasesDsl.() -> Unit) {
        dataBases = DataBasesDsl().apply(conf)
    }
}

fun profileSkills(conf: ProfileSkillsDsl.() -> Unit) = ProfileSkillsDsl().apply(conf).run {
    ProfileSkillsAndTech(
        profileId = id,
        specialization = SpecializationModel(specialization.category, specialization.subCategory),
        operatingSystems = operatingSystems.operatingSystemList,
        dataBases = dataBases.dataBaseList
    )
}
