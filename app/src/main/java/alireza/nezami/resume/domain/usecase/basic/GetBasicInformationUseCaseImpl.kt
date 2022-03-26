package alireza.nezami.resume.domain.usecase.basic

import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class GetBasicInformationUseCaseImpl(
    private val repository: BasicInformationRepository
) : GetBasicInformationUseCase {
    override suspend fun invoke(): Result<BasicInformation> = try {
        repository.getData()?.let {
            Result.success(it)
        } ?: Result.failure(RuntimeException("No Data"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}