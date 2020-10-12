package com.lpfun.profile.education

import com.lpfun.backend.common.model.error.InternalServerError
import com.lpfun.backend.common.model.profile.education.ProfileEducationContext
import com.lpfun.backend.kmp.profile.resultItem
import com.lpfun.backend.kmp.profile.setQuery
import com.lpfun.backend.profile.domain.education.ProfileEducationCrud
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationCreate
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationDelete
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationGet
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate

class ProfileEducationService(
    private val crud: ProfileEducationCrud
) {

    suspend fun get(query: KmpProfileEducationGet) = ProfileEducationContext().run {
        try {
            crud.get(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun create(query: KmpProfileEducationCreate) = ProfileEducationContext().run {
        try {
            crud.create(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun update(query: KmpProfileEducationUpdate) = ProfileEducationContext().run {
        try {
            crud.update(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }

    suspend fun delete(query: KmpProfileEducationDelete) = ProfileEducationContext().run {
        try {
            crud.delete(setQuery(query))
        } catch (t: Throwable) {
            errors.add(InternalServerError.instance)
        }
        resultItem()
    }
}