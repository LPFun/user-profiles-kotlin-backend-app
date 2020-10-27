package com.lpfun.backend.profile.inmemorydb.personal.entity

import com.lpfun.backend.profile.inmemorydb.personal.table.ProfilePersonalTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfilePersonalEntity(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, ProfilePersonalEntity>(ProfilePersonalTable)

    var firstName by ProfilePersonalTable.firstName
    var middleName by ProfilePersonalTable.middleName
    var lastName by ProfilePersonalTable.lastName
    var displayName by ProfilePersonalTable.displayName
    var phone by ProfilePersonalTable.phone
    var email by ProfilePersonalTable.email
    var bday by ProfilePersonalTable.bday
    var location by LocationEntity referencedOn ProfilePersonalTable.location
}