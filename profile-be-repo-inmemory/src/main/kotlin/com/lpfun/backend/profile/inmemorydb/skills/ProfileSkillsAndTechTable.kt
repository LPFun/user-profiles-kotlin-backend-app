package com.lpfun.backend.profile.inmemorydb.skills

import org.jetbrains.exposed.dao.id.IdTable

object ProfileSkillsAndTechTable : IdTable<String>() {
    override val id = varchar("profile_id", 50).entityId()
}