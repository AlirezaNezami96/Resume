package alireza.nezami.resume.domain.repository

import alireza.nezami.resume.data.database.entity.career.SkillEntity
import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.model.Skill

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface CareerRepository {

    suspend fun saveData(
        objective: String,
        totalYears: Double,
        skills: List<SkillEntity>
    )

    suspend fun getData(): Career?
}