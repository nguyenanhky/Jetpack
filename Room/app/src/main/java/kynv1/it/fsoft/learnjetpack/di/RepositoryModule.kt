package kynv1.it.fsoft.learnjetpack.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kynv1.it.fsoft.learnjetpack.database.dao.SchoolDAO
import kynv1.it.fsoft.learnjetpack.database.dao.StudentDAO
import kynv1.it.fsoft.learnjetpack.repository.ISchoolRepository
import kynv1.it.fsoft.learnjetpack.repository.SchoolRepository
import kynv1.it.fsoft.learnjetpack.utils.DispatcherProvider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSchoolRepository(
        studentDAO: StudentDAO,
        schoolDAO: SchoolDAO,
        dispatcherProvider: DispatcherProvider,
    ): ISchoolRepository {
        return SchoolRepository(studentDAO, schoolDAO, dispatcherProvider)
    }
}