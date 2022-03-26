package alireza.nezami.resume.domain.usecase.basic

import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface GetBasicInformationUseCase{
    suspend operator fun invoke() : Result<BasicInformation>
}