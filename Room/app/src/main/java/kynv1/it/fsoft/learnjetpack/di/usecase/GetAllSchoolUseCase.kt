package kynv1.it.fsoft.learnjetpack.di.usecase

import kotlinx.coroutines.flow.Flow
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.repository.ISchoolRepository
import javax.inject.Inject

class GetAllSchoolUseCase @Inject constructor(
    private val schoolRepository: ISchoolRepository
) {
    fun getAllUseCase() : Flow<List<School>> =schoolRepository.getAllSchool()
}