package kynv1.it.fsoft.learnjetpack.di.usecase

import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.repository.ISchoolRepository
import javax.inject.Inject

class DeleteStudentUseCase @Inject constructor(
    private val schoolRepository: ISchoolRepository
){
    suspend fun deleteStudent(student: Student){
        return schoolRepository.deleteStudent(student)
    }
}