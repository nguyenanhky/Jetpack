package kynv1.it.fsoft.learnjetpack.repository

import kotlinx.coroutines.flow.Flow
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.database.model.Student

interface ISchoolRepository {
    fun getAllSchool() : Flow<List<School>>

    suspend fun addSchool(school: School)

    suspend fun deleteSchool(school:School)
    suspend fun addStudent(student: Student)

    suspend fun deleteStudent(student: Student)

    suspend fun updateSchool(school: School)

    suspend fun updateStudent(student: Student)

}