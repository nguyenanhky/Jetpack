package kynv1.it.fsoft.learnjetpack.di.usecase

import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.repository.ISchoolRepository
import javax.inject.Inject

class DeleteSchoolUseCase @Inject constructor(
    private val schoolRepository: ISchoolRepository
){
    suspend fun deletSchool(school: School){
        return schoolRepository.deleteSchool(school)
    }
}