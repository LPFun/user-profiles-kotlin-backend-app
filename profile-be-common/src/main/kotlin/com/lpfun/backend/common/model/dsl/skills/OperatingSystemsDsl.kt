package com.lpfun.backend.common.model.dsl.skills

import com.lpfun.backend.common.model.dsl.ProfileDslMarker

@ProfileDslMarker
class OperatingSystemsDsl(
    val operatingSystemList: MutableSet<String> = mutableSetOf()
) {
    operator fun String.unaryPlus() {
        add(this)
    }

    fun add(string: String) {
        operatingSystemList.add(string)
    }

    companion object {
        val EMPTY = OperatingSystemsDsl()
    }
}
