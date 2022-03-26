package alireza.nezami.resume.domain.usecase.project

import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.model.Project
import alireza.nezami.resume.domain.repository.BasicInformationRepository
import alireza.nezami.resume.domain.repository.ProjectRepository
import alireza.nezami.resume.domain.usecase.project.GetProjectUseCase

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class GetProjectUseCaseImpl(
    private val repository: ProjectRepository
) : GetProjectUseCase {
    override suspend fun invoke(): Result<Project> = try {
        repository.getData()?.let {
            Result.success(it)
        } ?: Result.failure(RuntimeException("No Data"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}