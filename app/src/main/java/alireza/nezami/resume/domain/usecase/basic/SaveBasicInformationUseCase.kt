package alireza.nezami.resume.domain.usecase.basic

import alireza.nezami.resume.data.database.entity.career.SkillEntity
import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface SaveBasicInformationUseCase{
    suspend operator fun invoke(
        fullName: String,
        email: String,
        phoneNumber: String,
        address: String,
        image: ByteArray
    )
}