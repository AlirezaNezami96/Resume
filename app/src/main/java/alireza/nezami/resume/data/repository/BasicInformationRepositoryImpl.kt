package alireza.nezami.resume.data.repository

import alireza.nezami.resume.data.database.dao.BasicInformationDao
import alireza.nezami.resume.data.database.entity.basic.BasicInformationEntity
import alireza.nezami.resume.data.database.entity.basic.toDomainModel

import alireza.nezami.resume.domain.model.BasicInformation
import alireza.nezami.resume.domain.repository.BasicInformationRepository

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
class BasicInformationRepositoryImpl(
    private val basicInformationDao: BasicInformationDao
) : BasicInformationRepository {


    override suspend fun saveData(
        fullName: String,
        email: String,
        phoneNumber: String,
        address: String,
        image: ByteArray
    ) {
        basicInformationDao.insertBasicInformation(
            BasicInformationEntity(
                fullName = fullName,
                email = email,
                phoneNumber = phoneNumber,
                residenceAddress = address,
                image = image
            )
        )
    }

    override suspend fun getData(): BasicInformation {
        return basicInformationDao.getAll().toDomainModel()
    }


}