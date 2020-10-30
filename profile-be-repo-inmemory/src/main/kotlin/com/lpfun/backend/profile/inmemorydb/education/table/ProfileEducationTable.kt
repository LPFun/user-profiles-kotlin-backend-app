package com.lpfun.backend.profile.inmemorydb.education.table

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable

object ProfileEducationTable : IdTable<String>("profile_education_table") {
    override val id = varchar("profile_id", 50).entityId()
}

class ProfileEducationEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, ProfileEducationEntity>(ProfileEducationTable)
}