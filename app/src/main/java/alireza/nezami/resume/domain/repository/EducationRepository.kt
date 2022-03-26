package alireza.nezami.resume.domain.repository

import alireza.nezami.resume.data.database.entity.education.EducationDetailEntity
import alireza.nezami.resume.data.database.entity.education.EducationEntity
import alireza.nezami.resume.domain.model.*

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface EducationRepository {

    suspend fun saveData(
         educations: List<EducationDetailEntity>
    )

    suspend fun getData(): Education?
}