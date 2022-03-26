package alireza.nezami.resume.domain.usecase.work

import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.domain.repository.WorkRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class SaveWorkUseCaseImpl(
    private val repository: WorkRepository
) : SaveWorkUseCase {
    override suspend fun invoke(
        works: List<WorkSummaryEntity>
    ) {
        repository.saveData(works)
    }
}