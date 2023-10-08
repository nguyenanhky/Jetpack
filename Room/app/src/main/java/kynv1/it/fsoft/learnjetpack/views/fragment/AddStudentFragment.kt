package kynv1.it.fsoft.learnjetpack.views.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import kynv1.it.fsoft.learnjetpack.base.BaseDialogFragment
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.databinding.FragmentAddStudentBinding
import kynv1.it.fsoft.learnjetpack.views.SchoolListViewModel
import kynv1.it.fsoft.learnjetpack.views.adapter.SchoolSpinnerAdapter

@AndroidEntryPoint
class AddStudentFragment(
    private val viewModel : SchoolListViewModel,
    private val schools :List<School>) : BaseDialogFragment<FragmentAddStudentBinding>() {


    private var spinnerAdapter: SchoolSpinnerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    override fun initView() {
        initSchoolSpinner()
    }

    private fun initSchoolSpinner() {
        val schoolName = arrayListOf<String>()
        for (school in schools) {
            schoolName.add(school.schoolName)
        }
        spinnerAdapter = SchoolSpinnerAdapter(requireContext(), schools)
        binding.spinnerSchool.adapter = spinnerAdapter
    }

    override fun initAction() {
        binding.addStudent.setOnClickListener {
            val inputGrade = (binding.inputStudentGrade.text).toString()
            val studentname = (binding.inputStudentName.text).toString()
            if (studentname.isNullOrEmpty() || !inputGrade.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please input name and grade again !",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (inputGrade.removePrefix("-").removePrefix("+").all { it in '0'..'9' }) {
                viewModel.addNewStudent(getInputStudent(studentname, inputGrade))
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Grade is unavailable !", Toast.LENGTH_SHORT)
                    .show()
                initAction()
            }

        }
    }

    private fun getInputStudent(studentName: String, inputGrade: String): Student = with(binding) {

        val grade = if (inputGrade == "") 0 else inputGrade.toInt()
        return Student(
            studentName = studentName,
            studentGrade = grade,
            schoolID = (spinnerSchool.selectedItem as School).schoolID
        )


    }

    override fun getViewBinding(): FragmentAddStudentBinding =
        FragmentAddStudentBinding.inflate(layoutInflater)

    companion object {
        const val TAG = "AddStudent"
    }
}

