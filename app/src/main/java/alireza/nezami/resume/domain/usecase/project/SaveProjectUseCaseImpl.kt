package alireza.nezami.resume.domain.usecase.project

import alireza.nezami.resume.data.database.entity.project.ProjectDetailEntity
import alireza.nezami.resume.domain.repository.ProjectRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class SaveProjectUseCaseImpl(
    private val repository: ProjectRepository
) : SaveProjectUseCase {
    override suspend fun invoke(
        projects: List<ProjectDetailEntity>
    ) {
        repository.saveData(projects)
    }
}