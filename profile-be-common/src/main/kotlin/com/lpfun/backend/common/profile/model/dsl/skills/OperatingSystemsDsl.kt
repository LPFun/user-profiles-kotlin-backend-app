package com.lpfun.backend.common.profile.model.dsl.skills

import com.lpfun.backend.common.profile.model.dsl.ProfileDslMarker
import com.lpfun.backend.common.profile.model.profile.skills.OperatingSystemModel

@ProfileDslMarker
class OperatingSystemsDsl(
    val operatingSystemList: MutableSet<OperatingSystemModel> = mutableSetOf()
) {
    operator fun String.unaryPlus() {
        operatingSystemList.add(OperatingSystemModel(operatingSystem = this))
    }

    fun add(model: String) {
        operatingSystemList.add(OperatingSystemModel(operatingSystem = model))
    }

    companion object {
        val EMPTY = OperatingSystemsDsl()
    }
}
