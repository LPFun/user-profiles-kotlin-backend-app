package com.lpfun.backend.profile.db.base.skills

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object ProfileOperatingSystemTable : UUIDTable("profile_os_table") {
    val profileId = reference("profile_id", ProfileSkillsAndTechTable.id)
    val operatingSystem = text("operating_system")
}

class ProfileOperatingSystemEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ProfileOperatingSystemEntity>(ProfileOperatingSystemTable)

    var profileId by ProfileOperatingSystemTable.profileId
    var operatingSystem by ProfileOperatingSystemTable.operatingSystem
}