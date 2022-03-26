package alireza.nezami.resume.data.database.dao

import alireza.nezami.resume.data.database.entity.basic.BasicInformationEntity
import alireza.nezami.resume.data.database.entity.career.CareerEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CareerDao {

    @Query("SELECT * FROM career")
    suspend fun getAll(): CareerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCareer(info: CareerEntity)
}
