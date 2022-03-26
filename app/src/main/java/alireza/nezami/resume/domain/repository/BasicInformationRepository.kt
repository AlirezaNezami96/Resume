package alireza.nezami.resume.domain.repository

import alireza.nezami.resume.domain.model.BasicInformation

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface BasicInformationRepository {

    suspend fun saveData(
        fullName: String,
        email: String,
        phoneNumber: String,
        address: String,
        image: ByteArray
    )

    suspend fun getData(): BasicInformation?
}