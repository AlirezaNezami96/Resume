package alireza.nezami.resume.domain.usecase.project

import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.model.Project
import alireza.nezami.resume.domain.model.Works

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface GetProjectUseCase {
    suspend operator fun invoke(): Result<Project>
}