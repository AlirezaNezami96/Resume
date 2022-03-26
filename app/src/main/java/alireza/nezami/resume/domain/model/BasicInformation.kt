package alireza.nezami.resume.domain.model

import androidx.room.ColumnInfo

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
data class BasicInformation(
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val residenceAddress: String,
    val data: ByteArray?,
)
