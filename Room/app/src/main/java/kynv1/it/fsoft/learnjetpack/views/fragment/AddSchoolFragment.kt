package kynv1.it.fsoft.learnjetpack.views.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kynv1.it.fsoft.learnjetpack.base.BaseDialogFragment
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.databinding.FragmentAddSchoolBinding
import kynv1.it.fsoft.learnjetpack.views.SchoolListViewModel

@AndroidEntryPoint
class AddSchoolFragment: BaseDialogFragment<FragmentAddSchoolBinding>(){
    private val viewModel : SchoolListViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    override fun initAction() {
        binding.addSchool.setOnClickListener {


            val schoolName :String = (binding.inputSchooName.text ).toString()
            val schoolAddress :String =(binding.inputSchoolAddress.text ).toString()

            if(schoolName.isNullOrEmpty() || schoolAddress.isNullOrEmpty() ){
                Toast.makeText(requireContext(),"Please enter school name and school address again!",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.addNewSchool(getInputSchool(schoolName,schoolAddress))
            }

            val action = AddSchoolFragmentDirections.fromAddSchoolToList()
            findNavController().navigate(action)
        }
    }

    private fun getInputSchool(schoolName :String ,schoolAddress:String) : School {

        return School(
            schoolName = schoolName ,
            schoolAddress = schoolAddress
        )
    }



    override fun getViewBinding(): FragmentAddSchoolBinding =
        FragmentAddSchoolBinding.inflate(layoutInflater)

    companion object{
        const val  TAG ="AddSchool"
    }



}