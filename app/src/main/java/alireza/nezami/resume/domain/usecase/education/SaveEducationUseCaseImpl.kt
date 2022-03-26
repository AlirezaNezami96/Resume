package alireza.nezami.resume.domain.usecase.education

import alireza.nezami.resume.data.database.entity.education.EducationDetailEntity
import alireza.nezami.resume.domain.repository.BasicInformationRepository
import alireza.nezami.resume.domain.repository.EducationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class SaveEducationUseCaseImpl(
    private val repository: EducationRepository
) : SaveEducationUseCase {
    override suspend fun invoke(
        educations: List<EducationDetailEntity>
    ) {
        repository.saveData(educations)
    }
}