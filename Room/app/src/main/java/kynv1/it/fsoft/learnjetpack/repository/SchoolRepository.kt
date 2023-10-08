package kynv1.it.fsoft.learnjetpack.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kynv1.it.fsoft.learnjetpack.database.dao.SchoolDAO
import kynv1.it.fsoft.learnjetpack.database.dao.StudentDAO
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.utils.DispatcherProvider
import javax.inject.Inject


class SchoolRepository @Inject constructor(
    private val studentDAO: StudentDAO,
    private val schoolDAO: SchoolDAO,
    private val dispatcherProvider: DispatcherProvider
) :ISchoolRepository{
    override fun getAllSchool(): Flow<List<School>> {
        return schoolDAO.getSchoolAndStudents()
            .map{getStudentList -> getStudentList.map {
                    schoolAndStudents ->  schoolAndStudents.toSchool()
            }}
            .flowOn(dispatcherProvider.io)
    }

    override suspend fun addSchool(school: School){

        return withContext(dispatcherProvider.io){
            schoolDAO.insertSchool(school.toSchoolEntity())
        }

    }

    override suspend fun deleteSchool(school: School) {
        return withContext(dispatcherProvider.io){
            schoolDAO.deleteSchool(school.toSchoolEntity())
        }
    }

    override suspend fun addStudent(student: Student) {
        return withContext(dispatcherProvider.io){
            studentDAO.insertStudent(student.toStudentEntity())
        }
    }

    override suspend fun deleteStudent(student: Student) {
        return withContext(dispatcherProvider.io){
            studentDAO.deleteStudent(student.toStudentEntity())
        }
    }

    override suspend fun updateSchool(school: School) {
        return withContext(dispatcherProvider.io){
            schoolDAO.updateSchool(school.toSchoolEntity())
        }
    }

    override suspend fun updateStudent(student: Student) {
        return withContext(dispatcherProvider.io){
            studentDAO.updateStudent(student.toStudentEntity())
        }
    }


}
