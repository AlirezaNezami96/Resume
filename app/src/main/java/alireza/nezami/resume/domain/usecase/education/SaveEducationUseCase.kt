package alireza.nezami.resume.domain.usecase.education

import alireza.nezami.resume.data.database.entity.education.EducationDetailEntity
import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface SaveEducationUseCase{
    suspend operator fun invoke(
        educations: List<EducationDetailEntity>
    )
}