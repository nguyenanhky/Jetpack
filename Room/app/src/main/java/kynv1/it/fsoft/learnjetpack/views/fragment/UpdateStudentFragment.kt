package kynv1.it.fsoft.learnjetpack.views.fragment

import android.util.Log
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import kynv1.it.fsoft.learnjetpack.base.BaseDialogFragment
import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.databinding.FragmentUpdateStudentBinding
import kynv1.it.fsoft.learnjetpack.views.SchoolListViewModel

@AndroidEntryPoint
class UpdateStudentFragment(private val viewModel: SchoolListViewModel, private val student: Student) :
    BaseDialogFragment<FragmentUpdateStudentBinding>(){

    override fun getViewBinding(): FragmentUpdateStudentBinding = FragmentUpdateStudentBinding.inflate(layoutInflater)

    override fun initAction() {
        Log.d(TAG, "${student.studentID}")
        binding.updateStudent.setOnClickListener {
            val inputGrade = binding.updateStudentGrade.text.toString()

            if ( !inputGrade.isNullOrEmpty()){
                if(inputGrade.removePrefix("-").removePrefix("+").all{it in '0'..'9' }){
                    viewModel.updateStudent(
                        Student(
                            student.studentID ,binding.updateStudentName.text.toString() ,
                            Integer.parseInt( inputGrade), student.schoolID
                        )
                    )
                    dismiss()
                }
            }

            else{
                Toast.makeText(requireContext(),"Grade is unavailable !", Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object{
        const val  TAG ="UpdateStudent"
    }
}