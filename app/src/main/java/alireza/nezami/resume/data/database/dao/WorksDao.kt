package alireza.nezami.resume.data.database.dao

import alireza.nezami.resume.data.database.entity.basic.BasicInformationEntity
import alireza.nezami.resume.data.database.entity.career.CareerEntity
import alireza.nezami.resume.data.database.entity.works.WorksEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorksDao {

    @Query("SELECT * FROM works")
    suspend fun getAll(): WorksEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorks(entity: WorksEntity)
}
