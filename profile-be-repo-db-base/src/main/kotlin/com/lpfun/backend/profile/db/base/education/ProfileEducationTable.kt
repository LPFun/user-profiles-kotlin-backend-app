package com.lpfun.backend.profile.db.base.education

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable

object ProfileEducationTable : IdTable<String>("profile_education_table") {
    override val id = varchar("id", 50).entityId()
    override val primaryKey = PrimaryKey(id)
}

class ProfileEducationEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, ProfileEducationEntity>(ProfileEducationTable)
}