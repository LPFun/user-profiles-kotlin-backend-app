package com.lpfun.backend.profile.inmemorydb.education.table

import com.lpfun.backend.profile.inmemorydb.education.entity.MainEducationEntity
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable

object ProfileEducationTable : IdTable<String>("profile_education_table") {
    var profileId = varchar("profile_id", 50)
    override val primaryKey = PrimaryKey(profileId)
    override val id = profileId.entityId()
}

class ProfileEducationEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, ProfileEducationEntity>(ProfileEducationTable)

    val mains by MainEducationEntity referrersOn MainEducationTable.profileId
}