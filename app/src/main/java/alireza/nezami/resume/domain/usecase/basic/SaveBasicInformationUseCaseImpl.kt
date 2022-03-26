package alireza.nezami.resume.domain.usecase.basic

import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class SaveBasicInformationUseCaseImpl(
    private val repository: BasicInformationRepository
) : SaveBasicInformationUseCase {
    override suspend fun invoke(
        fullName: String,
        email: String,
        phoneNumber: String,
        address: String,
        image: ByteArray
    ) {
        repository.saveData(fullName, email, phoneNumber, address, image)
    }
}