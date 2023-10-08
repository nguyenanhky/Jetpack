package kynv1.it.fsoft.learnjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kynv1.it.fsoft.learnjetpack.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val userViewModel:UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.loggedIn.observe(viewLifecycleOwner) { statusLogin ->
            if (statusLogin.not()) {
                findNavController().navigate(R.id.action_homeFragment_to_signInFragment)
            }
        }
        binding.btnViewProfile.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(nameArg = userViewModel.getUsername()?:"")
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}