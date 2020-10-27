package com.lpfun.backend.profile.inmemorydb.education.entity

import com.lpfun.backend.profile.inmemorydb.education.table.MainEducationTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MainEducationEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, MainEducationEntity>(MainEducationTable)
    var profileId by MainEducationTable.profileId
    var university by MainEducationTable.university
    var department by MainEducationTable.department
    var speciality by MainEducationTable.speciality
    var yearOfCompletion by MainEducationTable.yearOfCompletion
}