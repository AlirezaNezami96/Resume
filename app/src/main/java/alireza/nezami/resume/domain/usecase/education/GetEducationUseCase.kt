package alireza.nezami.resume.domain.usecase.education

import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.model.Education
import alireza.nezami.resume.domain.model.Works

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface GetEducationUseCase {
    suspend operator fun invoke(): Result<Education>
}