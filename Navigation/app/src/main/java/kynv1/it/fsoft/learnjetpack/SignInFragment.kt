package kynv1.it.fsoft.learnjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kynv1.it.fsoft.learnjetpack.databinding.FragmentHomeBinding
import kynv1.it.fsoft.learnjetpack.databinding.FragmentProfileBinding
import kynv1.it.fsoft.learnjetpack.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            userViewModel.login(username, password).observe(viewLifecycleOwner) { sucessfull ->
                if (sucessfull) {
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Username and Password not correct",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}