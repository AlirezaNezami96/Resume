package alireza.nezami.resume.data.database.entity.works

import alireza.nezami.resume.data.database.entity.works.WorkSummaryEntity
import alireza.nezami.resume.domain.model.Works
import androidx.room.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Alireza Nezami on 3/26/2022.
 */
@Entity(tableName = "works")
@TypeConverters(WorkSummaryEntityTypeConverter::class)
data class WorksEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,

    @ColumnInfo(name = "work_list")
    val works: List<WorkSummaryEntity>
)

fun WorksEntity.toDomainModel() =
    Works(
        this.works.map { it.toDomainModel() },
    )

internal class WorkSummaryEntityTypeConverter {
    private val type = Types.newParameterizedType(List::class.java, WorkSummaryEntity::class.java)
    private val adapter: JsonAdapter<List<WorkSummaryEntity>> = Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<WorkSummaryEntity>): String =
        adapter.toJson(someObjects)
}

