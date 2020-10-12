package com.lpfun.profile.skillsandtech

import com.lpfun.backend.common.model.error.InternalServerError
import com.lpfun.backend.common.model.profile.skills.ProfileSkillsContext
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.profile.domain.skills.ProfileSkillsCrud
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechCreate
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechDelete
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechGet
import com.lpfun.transport.multiplatform.profile.skills.KmpProfileSkillsAndTechUpdate

class ProfileSkillsAndTechService(
    private val crud: ProfileSkillsCrud
) {
    suspend fun get(query: KmpProfileSkillsAndTechGet) = ProfileSkillsContext().run {
        try {
            crud.get(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun create(query: KmpProfileSkillsAndTechCreate) = ProfileSkillsContext().run {
        try {
            crud.create(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun update(query: KmpProfileSkillsAndTechUpdate) = ProfileSkillsContext().run {
        try {
            crud.update(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun delete(query: KmpProfileSkillsAndTechDelete) = ProfileSkillsContext().run {
        try {
            crud.update(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }
}