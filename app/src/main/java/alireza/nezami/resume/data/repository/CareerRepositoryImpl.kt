package alireza.nezami.resume.data.repository

import alireza.nezami.resume.data.database.dao.CareerDao
import alireza.nezami.resume.data.database.entity.basic.BasicInformationEntity
import alireza.nezami.resume.data.database.entity.career.CareerEntity
import alireza.nezami.resume.data.database.entity.career.SkillEntity
import alireza.nezami.resume.data.database.entity.career.toDomainModel
import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.model.Skill
import alireza.nezami.resume.domain.repository.CareerRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class CareerRepositoryImpl(
    private val careerDao: CareerDao
) : CareerRepository {


    override suspend fun saveData(objective: String, totalYears: Double, skills: List<SkillEntity>) {
        careerDao.insertCareer(
            CareerEntity(
                objective = objective,
                skills = skills,
                totalYears = totalYears
            )
        )
    }

    override suspend fun getData(): Career {
        return careerDao.getAll().toDomainModel()
    }


}