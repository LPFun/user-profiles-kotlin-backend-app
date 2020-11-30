package com.lpfun.backend.profile.db.base.skills

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object ProfileSpecializationTable : UUIDTable("profile_specialization_table") {
    val profileId = reference("profile_id", ProfileSkillsAndTechTable.id)
    val category = text("category")
    val subCategory = text("sub_category")
}

class ProfileSpecializationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ProfileSpecializationEntity>(ProfileSpecializationTable)

    var profileId by ProfileSpecializationTable.profileId
    var category by ProfileSpecializationTable.category
    var subCategory by ProfileSpecializationTable.subCategory
}