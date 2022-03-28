package alireza.nezami.resume.data.repository

import alireza.nezami.resume.data.database.dao.EducationsDao
import alireza.nezami.resume.data.database.entity.education.EducationDetailEntity
import alireza.nezami.resume.data.database.entity.education.EducationEntity
import alireza.nezami.resume.data.database.entity.education.toDomainModel
import alireza.nezami.resume.domain.model.Education
import alireza.nezami.resume.domain.repository.EducationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class EducationRepositoryImpl(
    private val educationsDao: EducationsDao
) : EducationRepository {

    override suspend fun saveData(educations: List<EducationDetailEntity>) {
        educationsDao.insertCareer(
            EducationEntity(
                educations = educations
            )
        )
    }

    override suspend fun getData(): Education {
        return educationsDao.getAll().toDomainModel()
    }


}