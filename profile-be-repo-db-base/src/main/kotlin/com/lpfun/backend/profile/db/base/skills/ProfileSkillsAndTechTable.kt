package com.lpfun.backend.profile.db.base.skills

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object ProfileSkillsAndTechTable : UUIDTable("profile_skills_tech_table")

class ProfileSkillsAndTechEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ProfileSkillsAndTechEntity>(ProfileSkillsAndTechTable)
}