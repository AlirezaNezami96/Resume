package alireza.nezami.resume.domain.usecase.education

import alireza.nezami.resume.domain.model.Education
import alireza.nezami.resume.domain.repository.EducationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class GetEducationUseCaseImpl(
    private val repository: EducationRepository
) : GetEducationUseCase {
    override suspend fun invoke(): Result<Education> = try {
        repository.getData()?.let {
            Result.success(it)
        } ?: Result.failure(RuntimeException("No Data"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}