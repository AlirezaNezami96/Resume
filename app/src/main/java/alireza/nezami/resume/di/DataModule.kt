package alireza.nezami.resume.di

import alireza.nezami.resume.data.database.ResumeDatabase
import alireza.nezami.resume.data.database.dao.*
import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Alireza Nezami on 3/19/2022.
 */

@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ResumeDatabase = Room.databaseBuilder(
        context,
        ResumeDatabase::class.java,
        "resume_database"
    ).build()

    @Singleton
    @Provides
    fun provideBasicInformationDao(
        database: ResumeDatabase
    ): BasicInformationDao = database.basic()

    @Singleton
    @Provides
    fun provideCareerDao(
        database: ResumeDatabase
    ): CareerDao = database.career()

    @Singleton
    @Provides
    fun provideWorkDao(
        database: ResumeDatabase
    ): WorksDao = database.work()

    @Singleton
    @Provides
    fun provideEducationDao(
        database: ResumeDatabase
    ): EducationsDao = database.education()

    @Singleton
    @Provides
    fun provideProjectDao(
        database: ResumeDatabase
    ): ProjectsDao = database.project()


    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


}