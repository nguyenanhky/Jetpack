package kynv1.it.fsoft.learnjetpack.di.usecase

import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.repository.ISchoolRepository
import javax.inject.Inject

class UpdateStudentUseCase @Inject constructor(
    private val schoolRepository: ISchoolRepository
){
    suspend fun updateStudent(student: Student){
        return schoolRepository.updateStudent(student)
    }
}