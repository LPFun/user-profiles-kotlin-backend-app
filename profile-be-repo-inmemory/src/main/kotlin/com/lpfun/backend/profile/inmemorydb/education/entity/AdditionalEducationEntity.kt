package com.lpfun.backend.profile.inmemorydb.education.entity

import com.lpfun.backend.profile.inmemorydb.education.table.AdditionalEducationTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AdditionalEducationEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, AdditionalEducationEntity>(AdditionalEducationTable)
    var profileId by AdditionalEducationTable.profileId
    var nameOfInstitution by AdditionalEducationTable.nameOfInstitution
    var courseName by AdditionalEducationTable.courseName
    var yearOfCompletion by AdditionalEducationTable.yearOfCompletion
}