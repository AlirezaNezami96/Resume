package alireza.nezami.resume.domain.usecase.project

import alireza.nezami.resume.data.database.entity.project.ProjectDetailEntity
import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface SaveProjectUseCase{
    suspend operator fun invoke(
        projects: List<ProjectDetailEntity>
    )
}