package alireza.nezami.resume.di

import alireza.nezami.resume.domain.repository.*
import alireza.nezami.resume.domain.usecase.basic.GetBasicInformationUseCase
import alireza.nezami.resume.domain.usecase.basic.GetBasicInformationUseCaseImpl
import alireza.nezami.resume.domain.usecase.basic.SaveBasicInformationUseCase
import alireza.nezami.resume.domain.usecase.basic.SaveBasicInformationUseCaseImpl
import alireza.nezami.resume.domain.usecase.career.GetCareersUseCase
import alireza.nezami.resume.domain.usecase.career.GetCareersUseCaseImpl
import alireza.nezami.resume.domain.usecase.career.SaveCareerUseCase
import alireza.nezami.resume.domain.usecase.career.SaveCareerUseCaseImpl
import alireza.nezami.resume.domain.usecase.education.GetEducationUseCase
import alireza.nezami.resume.domain.usecase.education.GetEducationUseCaseImpl
import alireza.nezami.resume.domain.usecase.education.SaveEducationUseCase
import alireza.nezami.resume.domain.usecase.education.SaveEducationUseCaseImpl
import alireza.nezami.resume.domain.usecase.project.GetProjectUseCase
import alireza.nezami.resume.domain.usecase.project.GetProjectUseCaseImpl
import alireza.nezami.resume.domain.usecase.project.SaveProjectUseCase
import alireza.nezami.resume.domain.usecase.project.SaveProjectUseCaseImpl
import alireza.nezami.resume.domain.usecase.work.GetWorkUseCase
import alireza.nezami.resume.domain.usecase.work.GetWorkUseCaseImpl
import alireza.nezami.resume.domain.usecase.work.SaveWorkUseCase
import alireza.nezami.resume.domain.usecase.work.SaveWorkUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetBasicInformationUseCase(
        basicInformationRepository: BasicInformationRepository
    ): GetBasicInformationUseCase = GetBasicInformationUseCaseImpl(basicInformationRepository)

    @Singleton
    @Provides
    fun provideSaveBasicInformationUseCase(
        basicInformationRepository: BasicInformationRepository
    ): SaveBasicInformationUseCase = SaveBasicInformationUseCaseImpl(basicInformationRepository)


    @Singleton
    @Provides
    fun provideGetCareerUseCase(
        careerRepository: CareerRepository
    ): GetCareersUseCase = GetCareersUseCaseImpl(careerRepository)

    @Singleton
    @Provides
    fun provideSaveCareerUseCase(
        careerRepository: CareerRepository
    ): SaveCareerUseCase = SaveCareerUseCaseImpl(careerRepository)


    @Singleton
    @Provides
    fun provideGetWorkUseCase(
        workRepository: WorkRepository
    ): GetWorkUseCase = GetWorkUseCaseImpl(workRepository)

    @Singleton
    @Provides
    fun provideSaveWorkUseCase(
        workRepository: WorkRepository
    ): SaveWorkUseCase = SaveWorkUseCaseImpl(workRepository)

    @Singleton
    @Provides
    fun provideGetEducationUseCase(
        educationRepository: EducationRepository
    ): GetEducationUseCase = GetEducationUseCaseImpl(educationRepository)

    @Singleton
    @Provides
    fun provideSaveEducationUseCase(
        educationRepository: EducationRepository
    ): SaveEducationUseCase = SaveEducationUseCaseImpl(educationRepository)

    @Singleton
    @Provides
    fun provideGetProjectUseCase(
        projectRepository: ProjectRepository
    ): GetProjectUseCase = GetProjectUseCaseImpl(projectRepository)

    @Singleton
    @Provides
    fun provideSaveProjectUseCase(
        projectRepository: ProjectRepository
    ): SaveProjectUseCase = SaveProjectUseCaseImpl(projectRepository)


}