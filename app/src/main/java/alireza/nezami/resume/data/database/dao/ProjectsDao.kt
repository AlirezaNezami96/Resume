package alireza.nezami.resume.data.database.dao

import alireza.nezami.resume.data.database.entity.basic.BasicInformationEntity
import alireza.nezami.resume.data.database.entity.career.CareerEntity
import alireza.nezami.resume.data.database.entity.project.ProjectEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProjectsDao {

    @Query("SELECT * FROM project")
    suspend fun getAll(): ProjectEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCareer(entity: ProjectEntity)
}
