package alireza.nezami.resume.data.database.entity.project

import alireza.nezami.resume.domain.model.Project
import androidx.room.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
@Entity(tableName = "project")
@TypeConverters(ProjectEntityTypeConverter::class)
data class ProjectEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,

    @ColumnInfo(name = "project_list")
    val projects: List<ProjectDetailEntity>
)

fun ProjectEntity.toDomainModel() =
    Project(
        this.projects.map { it.toDomainModel() },
    )

internal class ProjectEntityTypeConverter {
    private val type = Types.newParameterizedType(List::class.java, ProjectDetailEntity::class.java)
    private val adapter: JsonAdapter<List<ProjectDetailEntity>> =
        Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<ProjectDetailEntity>): String =
        adapter.toJson(someObjects)
}

