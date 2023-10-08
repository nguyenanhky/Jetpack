package kynv1.it.fsoft.learnjetpack.views

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kynv1.it.fsoft.learnjetpack.base.BaseFragment
import kynv1.it.fsoft.learnjetpack.base.SchoolViewState
import kynv1.it.fsoft.learnjetpack.database.model.School
import kynv1.it.fsoft.learnjetpack.database.model.Student
import kynv1.it.fsoft.learnjetpack.databinding.FragmentSchoolListBinding
import kynv1.it.fsoft.learnjetpack.utils.studentNumbers
import kynv1.it.fsoft.learnjetpack.views.adapter.ClickItemListener
import kynv1.it.fsoft.learnjetpack.views.adapter.SchoolAdapter
import kynv1.it.fsoft.learnjetpack.views.fragment.AddStudentFragment
import kynv1.it.fsoft.learnjetpack.views.fragment.UpdateSchoolFragment
import kynv1.it.fsoft.learnjetpack.views.fragment.UpdateStudentFragment

@AndroidEntryPoint
class SchoolListFragment : BaseFragment<FragmentSchoolListBinding>(), ClickItemListener {

    private val viewModel: SchoolListViewModel by activityViewModels()
    private val schoolAdapter = SchoolAdapter(this)


    override fun getViewBinding(): FragmentSchoolListBinding =
        FragmentSchoolListBinding.inflate(layoutInflater)

    override fun observerLiveData() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SchoolViewState.GetSchoolSuccess -> loadSchools(state.school)

            }
        }
    }

    private fun loadSchools(schools: List<School>?) {
        schoolAdapter.submitList(schools)
        binding.tvSchoolNum.text=(schools?.size ?: 0).toString()
        binding.tvStudentNum.text=(schools?.studentNumbers() ?:0).toString()
    }

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = this@SchoolListFragment.viewModel
        binding.rcvSchool.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rcvSchool.adapter = schoolAdapter
        viewModel.getSchoolList()
    }

    override fun initAction() {
        binding.btnAddSchool.setOnClickListener {
            val action = SchoolListFragmentDirections.fromListToAddSchool()
            findNavController().navigate(action)
        }
        binding.btnAddStudent.setOnClickListener {
            val schools =viewModel.schoolList.value
            if(schools.isNullOrEmpty())
                Toast.makeText(requireContext(),"You have to input school first !" , Toast.LENGTH_SHORT).show()
            else{
                AddStudentFragment(viewModel,viewModel.schoolList.value?: listOf()).show(
                    childFragmentManager,AddStudentFragment.TAG)

            }

        }

    }

    override fun onClickDeleteSchool(school: School) {
        viewModel.deleteSchool(school)
    }


    override fun onClickDeleteStudent(student: Student) {
        viewModel.deleteStudent(student)
    }

    override fun onClickUpdateSchool(school: School) {
        UpdateSchoolFragment(viewModel,school).show(childFragmentManager, UpdateStudentFragment.TAG)

    }

    override fun onClickUpdateStudent(student: Student) {
        UpdateStudentFragment(viewModel,student).show(childFragmentManager,UpdateStudentFragment.TAG)

    }
}




