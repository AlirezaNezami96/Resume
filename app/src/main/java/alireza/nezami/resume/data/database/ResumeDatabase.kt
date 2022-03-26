package alireza.nezami.resume.data.database

import alireza.nezami.resume.data.database.dao.*
import alireza.nezami.resume.data.database.entity.basic.BasicInformationEntity
import alireza.nezami.resume.data.database.entity.career.CareerEntity
import alireza.nezami.resume.data.database.entity.education.EducationEntity
import alireza.nezami.resume.data.database.entity.project.ProjectEntity
import alireza.nezami.resume.data.database.entity.works.WorksEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BasicInformationEntity::class,
        CareerEntity::class,
        WorksEntity::class,
        EducationEntity::class,
        ProjectEntity::class,
    ], version = 1, exportSchema = false
)
abstract class ResumeDatabase : RoomDatabase() {

    abstract fun basic(): BasicInformationDao
    abstract fun career(): CareerDao
    abstract fun work(): WorksDao
    abstract fun education(): EducationsDao
    abstract fun project(): ProjectsDao
}
