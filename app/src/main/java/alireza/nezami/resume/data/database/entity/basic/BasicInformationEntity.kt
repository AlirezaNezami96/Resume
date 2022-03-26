package alireza.nezami.resume.data.database.entity.basic

import alireza.nezami.resume.domain.model.BasicInformation
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alireza Nezami on 3/18/2022.
 */

@Entity(tableName = "basic")
data class BasicInformationEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,

    @ColumnInfo(name = "fullName")
    val fullName: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String,

    @ColumnInfo(name = "residenceAddress")
    val residenceAddress: String,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?,

    )

internal fun BasicInformationEntity.toDomainModel() =
    BasicInformation(
        this.fullName,
        this.email,
        this.phoneNumber,
        this.residenceAddress,
        this.image,
    )