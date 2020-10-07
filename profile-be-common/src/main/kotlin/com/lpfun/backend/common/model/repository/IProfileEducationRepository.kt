package com.lpfun.backend.common.model.repository

import com.lpfun.backend.common.model.profile.education.ProfileEducation

interface IProfileEducationRepository {
    fun get(porfileId: String): ProfileEducation
    fun delete(porfileId: String): ProfileEducation
    fun create(profile: ProfileEducation): ProfileEducation
    fun update(profile: ProfileEducation): ProfileEducation
}