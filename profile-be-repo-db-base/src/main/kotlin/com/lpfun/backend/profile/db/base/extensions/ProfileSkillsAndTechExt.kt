package com.lpfun.backend.profile.db.extensions

import com.lpfun.backend.common.profile.model.profile.skills.DataBaseModel
import com.lpfun.backend.common.profile.model.profile.skills.OperatingSystemModel
import com.lpfun.backend.common.profile.model.profile.skills.SpecializationModel
import com.lpfun.backend.profile.db.base.skills.ProfileDataBaseEntity
import com.lpfun.backend.profile.db.base.skills.ProfileOperatingSystemEntity
import com.lpfun.backend.profile.db.base.skills.ProfileSpecializationEntity

fun ProfileOperatingSystemEntity.toModel() =
    OperatingSystemModel(id = profileId.toString(), operatingSystem = operatingSystem)

fun ProfileDataBaseEntity.toModel() =
    DataBaseModel(id = profileId.toString(), dataBase = dataBase)

fun ProfileSpecializationEntity?.toSpecializationModel(): SpecializationModel {
    return this?.let {
        SpecializationModel(
            it.category,
            it.subCategory
        )
    } ?: SpecializationModel.NONE
}

fun Iterable<ProfileDataBaseEntity>.toDataBasesModel(): MutableSet<DataBaseModel> {
    val list = mutableSetOf<DataBaseModel>()
    forEach {
        list.add(it.toModel())
    }
    return list
}

fun Iterable<ProfileOperatingSystemEntity>.toOperatingSystemsModel(): MutableSet<OperatingSystemModel> {
    val list = mutableSetOf<OperatingSystemModel>()
    forEach {
        list.add(it.toModel())
    }
    return list
}
