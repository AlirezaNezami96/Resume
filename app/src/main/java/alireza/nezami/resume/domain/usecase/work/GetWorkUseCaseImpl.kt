package alireza.nezami.resume.domain.usecase.work

import alireza.nezami.resume.domain.model.Works
import alireza.nezami.resume.domain.repository.WorkRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class GetWorkUseCaseImpl(
    private val repository: WorkRepository
) : GetWorkUseCase {
    override suspend fun invoke(): Result<Works> = try {
        repository.getData()?.let {
            Result.success(it)
        } ?: Result.failure(RuntimeException("No Data"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}