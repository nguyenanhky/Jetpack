package kynv1.it.fsoft.learnjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kynv1.it.fsoft.learnjetpack.databinding.FragmentSignInBinding
import kynv1.it.fsoft.learnjetpack.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val userViewModel:UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateAccount.setOnClickListener {
            val username = binding.edtCreateUsername.text.toString()
            val password = binding.edtCreatePassword.text.toString()
            userViewModel.singUp(username,password).observe(viewLifecycleOwner){successful->
                if(successful){
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(requireContext(), "Username or Password is not empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}