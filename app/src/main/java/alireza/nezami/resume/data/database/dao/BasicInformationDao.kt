package alireza.nezami.resume.data.database.dao

import alireza.nezami.resume.data.database.entity.basic.BasicInformationEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BasicInformationDao {

    @Query("SELECT * FROM basic")
    suspend fun getAll(): BasicInformationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasicInformation(info: BasicInformationEntity)
}
