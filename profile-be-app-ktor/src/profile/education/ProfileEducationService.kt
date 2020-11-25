package com.lpfun.profile.education

import com.lpfun.backend.common.profile.model.profile.education.ProfileEducationContext
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.profile.domain.education.ProfileEducationCrud
import com.lpfun.base.request
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate

class ProfileEducationService(
    private val crud: ProfileEducationCrud
) {
    suspend fun get(query: KmpProfileEducationGet) = ProfileEducationContext().request {
        crud.get(setQuery(query))
    }

    suspend fun create(query: KmpProfileEducationCreate) = ProfileEducationContext().request {
        crud.create(setQuery(query))
    }

    suspend fun update(query: KmpProfileEducationUpdate) = ProfileEducationContext().request {
        crud.update(setQuery(query))
    }

    suspend fun delete(query: KmpProfileEducationDelete) = ProfileEducationContext().request {
        crud.delete(setQuery(query))
    }
}