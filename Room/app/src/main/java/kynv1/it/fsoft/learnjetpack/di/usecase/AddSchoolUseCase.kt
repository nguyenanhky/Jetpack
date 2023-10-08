package kynv1.it.fsoft.learnjetpack.di.usecase

import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.repository.ISchoolRepository
import javax.inject.Inject

class AddSchoolUseCase @Inject constructor(
    private val schoolRepository: ISchoolRepository
) {
    suspend fun addSchool(school: School){
        return schoolRepository.addSchool(school)
    }
}