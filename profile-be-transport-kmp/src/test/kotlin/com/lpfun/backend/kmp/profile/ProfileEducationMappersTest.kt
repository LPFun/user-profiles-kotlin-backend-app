package com.lpfun.backend.kmp.profile

import com.lpfun.backend.common.profile.model.profile.education.AdditionalEducationModel
import com.lpfun.backend.common.profile.model.profile.education.EducationModel
import com.lpfun.backend.common.profile.model.profile.education.ProfileEducation
import com.lpfun.transport.multiplatform.profile.education.KmpProfileEducationUpdate
import com.lpfun.transport.multiplatform.profile.education.model.KmpAdditionalEducationModel
import com.lpfun.transport.multiplatform.profile.education.model.KmpEducationModel
import org.junit.Test
import kotlin.test.assertEquals

internal class ProfileEducationMappersTest {

    @Test
    fun kmpToModel() {
        val kmpProfileEducation = KmpProfileEducationUpdate(
            profileId = "121",
            mainEducation = getTestKmpEducationModel(),
            additionalEducation = getTestKmpAdditionalModel()
        )

        val profileEducationModel = kmpProfileEducation.toModel()

        assertEquals("121", profileEducationModel.profileId)
        assertEquals(
            getTestEducationModel(),
            profileEducationModel.mainEducation
        )
        assertEquals(
            getTestAdditionalEducationModel(),
            profileEducationModel.additionalEducation
        )
    }

    @Test
    fun modelToKmp() {
        val profileEducation = ProfileEducation(
            profileId = "123",
            mainEducation = getTestEducationModel(),
            additionalEducation = getTestAdditionalEducationModel()
        )
        val kmpProfileEducation = profileEducation.toKmp()
        assertEquals("123", kmpProfileEducation.profileId)
        assertEquals(getTestKmpEducationModel(), kmpProfileEducation.mainEducation)
        assertEquals(getTestKmpAdditionalModel(), kmpProfileEducation.additionalEducation)
    }

    private fun getTestKmpEducationModel() = mutableListOf(
        KmpEducationModel(
            university = "MGU",
            department = "IT",
            specialty = "Programmer",
            yearOfCompletion = "2020"
        )
    )

    private fun getTestKmpAdditionalModel() = mutableListOf(
        KmpAdditionalEducationModel(
            nameOfInstitution = "OTUS",
            courseName = "Kotlin",
            yearOfCompletion = "2020"
        )
    )

    private fun getTestEducationModel() = mutableListOf(
        EducationModel(
            university = "MGU",
            department = "IT",
            specialty = "Programmer",
            yearOfCompletion = "2020"
        )
    )

    private fun getTestAdditionalEducationModel() = mutableListOf(
        AdditionalEducationModel(
            nameOfInstitution = "OTUS",
            courseName = "Kotlin",
            yearOfCompletion = "2020"
        )
    )
}