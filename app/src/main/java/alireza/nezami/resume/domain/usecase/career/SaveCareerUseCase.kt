package alireza.nezami.resume.domain.usecase.career

import alireza.nezami.resume.data.database.entity.career.SkillEntity
import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface SaveCareerUseCase{
    suspend operator fun invoke(
        objective: String,
        totalYears: Double,
        skills: List<SkillEntity>
    )
}