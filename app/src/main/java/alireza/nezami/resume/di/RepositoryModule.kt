package alireza.nezami.resume.di

import alireza.nezami.resume.data.database.dao.BasicInformationDao
import alireza.nezami.resume.data.repository.BasicInformationRepositoryImpl
import alireza.nezami.resume.domain.repository.BasicInformationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
@Module(includes = [DataModule::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBasicInformationRepository(
        basicInformationDao: BasicInformationDao
    ): BasicInformationRepository =
        BasicInformationRepositoryImpl(
            basicInformationDao
        )
}