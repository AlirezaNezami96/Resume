package alireza.nezami.resume.domain.usecase.work

import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface SaveWorkUseCase{
    suspend operator fun invoke(
        works: List<WorkSummaryEntity>
    )
}