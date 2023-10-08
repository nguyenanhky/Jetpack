package kynv1.it.fsoft.learnjetpack.views.fragment

import dagger.hilt.android.AndroidEntryPoint
import kynv1.it.fsoft.learnjetpack.base.BaseDialogFragment
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.databinding.FragmentUpdateSchoolBinding
import kynv1.it.fsoft.learnjetpack.views.SchoolListViewModel

@AndroidEntryPoint
class UpdateSchoolFragment(private val viewModel : SchoolListViewModel, private val school: School)
    : BaseDialogFragment<FragmentUpdateSchoolBinding>() {


    override fun getViewBinding(): FragmentUpdateSchoolBinding = FragmentUpdateSchoolBinding.inflate(layoutInflater)


    override fun initAction() {
        binding.updateSchool.setOnClickListener {
            viewModel.updateSchool(
                School(
                    school.schoolID,binding.updateSchoolName.text.toString(),binding.updateSchoolAddress.text.toString()
                )
            )
            dismiss()
        }
    }


    companion object{
        const val  TAG ="UpdateSchool"
    }
}