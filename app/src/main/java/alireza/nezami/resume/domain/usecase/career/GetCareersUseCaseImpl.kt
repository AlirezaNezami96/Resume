package alireza.nezami.resume.domain.usecase.career

import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.repository.BasicInformationRepository
import alireza.nezami.resume.domain.repository.CareerRepository
import alireza.nezami.resume.domain.usecase.basic.GetBasicInformationUseCase

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class GetCareersUseCaseImpl(
    private val repository: CareerRepository
) : GetCareersUseCase {
    override suspend fun invoke(): Result<Career> = try {
        repository.getData()?.let {
            Result.success(it)
        } ?: Result.failure(RuntimeException("No Data"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}