package alireza.nezami.resume.domain.usecase.career

import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface GetCareersUseCase{
    suspend operator fun invoke() : Result<Career>
}