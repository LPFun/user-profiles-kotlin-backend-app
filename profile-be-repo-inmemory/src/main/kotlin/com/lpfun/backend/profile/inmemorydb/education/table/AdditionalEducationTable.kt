package com.lpfun.backend.profile.inmemorydb.education.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object AdditionalEducationTable : IdTable<String>("additional_education_table") {
    val additionalEducationId = varchar("id", 50)
    val profileId = varchar("profileId", 50).references(ProfileEducationTable.profileId)
    val nameOfInstitution = text("name_of_institution")
    val courseName = text("course_name")
    val yearOfCompletion = text("year_of_completion")
    override val id: Column<EntityID<String>> = additionalEducationId.entityId()

}