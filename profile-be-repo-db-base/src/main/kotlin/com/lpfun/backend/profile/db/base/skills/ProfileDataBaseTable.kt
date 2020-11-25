package com.lpfun.backend.profile.db.base.skills

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object ProfileDataBaseTable : UUIDTable("profile_db_table") {
    val profileId = reference("profile_id", ProfileSkillsAndTechTable.id)
    val dataBase = text("data_base")
}

class ProfileDataBaseEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ProfileDataBaseEntity>(ProfileDataBaseTable)

    var profileId by ProfileDataBaseTable.profileId
    var dataBase by ProfileDataBaseTable.dataBase
}