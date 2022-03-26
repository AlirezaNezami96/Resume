package alireza.nezami.resume.domain.usecase.career

import alireza.nezami.resume.data.database.entity.career.SkillEntity
import alireza.nezami.resume.domain.repository.CareerRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class SaveCareerUseCaseImpl(
    private val repository: CareerRepository
) : SaveCareerUseCase {
    override suspend fun invoke(
        objective: String,
        totalYears: Double,
        skills: List<SkillEntity>
    ) {
        repository.saveData(objective, totalYears, skills)
    }
}