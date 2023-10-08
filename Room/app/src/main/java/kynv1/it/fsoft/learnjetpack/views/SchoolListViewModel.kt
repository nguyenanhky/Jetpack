package kynv1.it.fsoft.learnjetpack.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kynv1.it.fsoft.learnjetpack.base.BaseViewModel
import kynv1.it.fsoft.learnjetpack.base.SchoolViewState
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.di.usecase.AddSchoolUseCase
import kynv1.it.fsoft.learnjetpack.di.usecase.AddStudentUseCase
import kynv1.it.fsoft.learnjetpack.di.usecase.DeleteSchoolUseCase
import kynv1.it.fsoft.learnjetpack.di.usecase.DeleteStudentUseCase
import kynv1.it.fsoft.learnjetpack.di.usecase.GetAllSchoolUseCase
import kynv1.it.fsoft.learnjetpack.di.usecase.UpdateSchoolUseCase
import kynv1.it.fsoft.learnjetpack.di.usecase.UpdateStudentUseCase
import javax.inject.Inject


@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val getAllSchoolUseCase: GetAllSchoolUseCase,
    private val addSchoolUseCase: AddSchoolUseCase,
    private val addStudentUseCase: AddStudentUseCase,
    private val deleteSchoolUseCase: DeleteSchoolUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
    private val updateSchoolUseCase: UpdateSchoolUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase
) : BaseViewModel<SchoolViewState>() {


    private val _schoolList = MutableLiveData<List<School>>()
    val schoolList: LiveData<List<School>>
        get() = _schoolList

    fun getSchoolList() {
        viewModelScope.launch {
            getAllSchoolUseCase.getAllUseCase().catch { ex ->
                _viewState.value = SchoolViewState.FailOperator(ex.localizedMessage ?: "")
            }.collect { value ->
                _viewState.value = SchoolViewState.GetSchoolSuccess(value)
                _schoolList.value = value
            }
        }
    }

    fun addNewStudent(student: Student) {
        viewModelScope.launch {
            addStudentUseCase.addStudent(student)
        }
    }

    fun addNewSchool(school: School) {
        viewModelScope.launch {
            addSchoolUseCase.addSchool(school)
        }
    }

    fun deleteSchool(school: School) {
        viewModelScope.launch {
            deleteSchoolUseCase.deletSchool(school)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            deleteStudentUseCase.deleteStudent(student)
        }
    }

    fun updateSchool(school: School) {
        viewModelScope.launch {
            updateSchoolUseCase.updateSchool(school)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            updateStudentUseCase.updateStudent(student)
        }
    }
}


