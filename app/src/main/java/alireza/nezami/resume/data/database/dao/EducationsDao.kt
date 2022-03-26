package alireza.nezami.resume.data.database.dao

import alireza.nezami.resume.data.database.entity.education.EducationEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EducationsDao {

    @Query("SELECT * FROM education")
    suspend fun getAll(): EducationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCareer(entity: EducationEntity)
}
