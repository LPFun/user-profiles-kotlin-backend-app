package com.lpfun.backend.common.model.dsl.skills

import com.lpfun.backend.common.model.dsl.ProfileDslMarker
import com.lpfun.backend.common.model.profile.skills.DataBaseModel

@ProfileDslMarker
class DataBasesDsl {
    val dataBaseList = mutableSetOf<DataBaseModel>()

    fun add(dataBase: String) {
        dataBaseList.add(DataBaseModel(dataBase = dataBase))
    }

    operator fun String.unaryPlus() {
        dataBaseList.add(DataBaseModel(dataBase = this))
    }

    companion object {
        val EMPTY = DataBasesDsl()
    }
}
