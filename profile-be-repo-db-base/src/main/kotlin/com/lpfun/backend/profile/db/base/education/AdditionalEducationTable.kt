package com.lpfun.backend.profile.db.base.education

import org.jetbrains.exposed.dao.id.IdTable

object AdditionalEducationTable : IdTable<String>("additional_education_table") {
    val profileId = reference("profile_id", ProfileEducationTable.id)
    val nameOfInstitution = text("name_of_institution")
    val courseName = text("course_name")
    val yearOfCompletion = text("year_of_completion")
    override val id = varchar("id", 50).entityId()
}