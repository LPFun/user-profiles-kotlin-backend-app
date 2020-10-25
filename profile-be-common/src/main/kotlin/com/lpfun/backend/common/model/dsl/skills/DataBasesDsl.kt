package com.lpfun.backend.common.model.dsl.skills

import com.lpfun.backend.common.model.dsl.ProfileDslMarker

@ProfileDslMarker
class DataBasesDsl {
    val dataBaseList = mutableSetOf<String>()

    fun add(dataBaseStr: String) {
        dataBaseList.add(dataBaseStr)
    }

    operator fun String.unaryPlus() {
        add(this)
    }

    companion object {
        val EMPTY = DataBasesDsl()
    }
}
