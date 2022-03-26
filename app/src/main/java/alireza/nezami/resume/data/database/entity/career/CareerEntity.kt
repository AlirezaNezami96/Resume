package alireza.nezami.resume.data.database.entity.career

import alireza.nezami.resume.data.database.entity.career.SkillEntity
import alireza.nezami.resume.data.database.entity.career.toDomainModel
import alireza.nezami.resume.domain.model.Career
import androidx.room.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Alireza Nezami on 3/18/2022.
 */

@Entity(tableName = "career")
@TypeConverters(CareerEntityTypeConverter::class)
data class CareerEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,

    @ColumnInfo(name = "objective")
    val objective: String,

    @ColumnInfo(name = "total_years")
    val totalYears: Double,

    @ColumnInfo(name = "skills")
    val skills: List<SkillEntity>

)

internal class CareerEntityTypeConverter {
    private val type = Types.newParameterizedType(List::class.java, SkillEntity::class.java)
    private val adapter: JsonAdapter<List<SkillEntity>> = Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<SkillEntity>): String =
        adapter.toJson(someObjects)
}


internal fun CareerEntity.toDomainModel() =
    Career(
        objective,
        totalYears,
        this.skills.map { it.toDomainModel() },
    )