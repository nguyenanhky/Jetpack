package kynv1.it.fsoft.learnjetpack.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kynv1.it.fsoft.learnjetpack.database.SchoolDatabase
import kynv1.it.fsoft.learnjetpack.database.dao.SchoolDAO
import kynv1.it.fsoft.learnjetpack.database.dao.StudentDAO
import kynv1.it.fsoft.learnjetpack.utils.DispatcherProvider
import kynv1.it.fsoft.learnjetpack.utils.StandardDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideSchoolDAO(schoolDatabase: SchoolDatabase) : SchoolDAO {
        return schoolDatabase.schoolDAO()
    }

    @Provides
    fun provideStudentDAO(schoolDatabase: SchoolDatabase) : StudentDAO {
        return schoolDatabase.studentDAO()
    }

    @Provides
    @Singleton
    fun provideSchoolDatabase(application: Application):SchoolDatabase= SchoolDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providerDispatcherProviders(): DispatcherProvider = StandardDispatcher()
}