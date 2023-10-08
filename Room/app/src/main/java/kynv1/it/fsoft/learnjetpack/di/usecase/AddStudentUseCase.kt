package kynv1.it.fsoft.learnjetpack.di.usecase

import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.repository.ISchoolRepository
import javax.inject.Inject

class AddStudentUseCase @Inject constructor(
    private val schoolRepository: ISchoolRepository
) {
    suspend fun addStudent(student: Student){
        return schoolRepository.addStudent(student)
    }
}