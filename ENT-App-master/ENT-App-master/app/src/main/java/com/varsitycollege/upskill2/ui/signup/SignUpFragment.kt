package com.varsitycollege.upskill2.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.varsitycollege.upskill2.R
import com.varsitycollege.upskill2.databinding.FragmentSignupBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set up click listener for the sign-up button
        binding.signUpBtn.setOnClickListener {
            val email = binding.emailTxtInp.text.toString().trim()
            val password = binding.passwordTxtInp.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Show the progress bar
                binding.progressBarLayout.visibility = View.VISIBLE

                // Start sign-up process
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        // Hide the progress bar
                        binding.progressBarLayout.visibility = View.GONE

                        if (task.isSuccessful) {
                            // Sign-up success, navigate to the home fragment
                            findNavController().navigate(R.id.nav_home)
                        } else {
                            // If sign-up fails, display a message to the user
                            Toast.makeText(
                                requireContext(),
                                "Registration failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up click listener for the login screen button
        binding.loginScrnBtn.setOnClickListener {
            // Navigate to LoginFragment using the navController
            findNavController().navigate(R.id.nav_login)
        }
    }
}
