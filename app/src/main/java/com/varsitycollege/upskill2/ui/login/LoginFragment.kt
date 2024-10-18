package com.varsitycollege.upskill2.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.varsitycollege.upskill2.R
import com.varsitycollege.upskill2.databinding.FragmentLoginBinding
import com.varsitycollege.upskill2.ui.login.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set up click listener for the login button
        binding.loginBtn.setOnClickListener {
            val email = binding.emailTxtInp.text.toString().trim()
            val password = binding.passwordTxtInp.text.toString().trim()

            // Validate inputs
            var valid = true
            if (!isValidEmail(email)) {
                binding.emailTxtInp.error = "Please enter a valid email"
                valid = false
            }
            if (password.length < 6) {
                binding.passwordTxtInp.error = "Password must be at least 6 characters"
                valid = false
            }

            if (!valid) return@setOnClickListener

            // Clear errors on success
            binding.emailTxtInp.error = null
            binding.passwordTxtInp.error = null

            // Show progress bar and disable button to prevent multiple clicks
            binding.progressBar.visibility = View.VISIBLE
            binding.loginBtn.isEnabled = false
            binding.emailTxtInp.isEnabled = false
            binding.passwordTxtInp.isEnabled = false

            // Sign in with Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    // Hide progress bar
                    binding.progressBar.visibility = View.GONE
                    binding.loginBtn.isEnabled = true
                    binding.emailTxtInp.isEnabled = true
                    binding.passwordTxtInp.isEnabled = true

                    if (task.isSuccessful) {
                        // Login success, navigate to home page
                        findNavController().navigate(R.id.nav_home)
                    } else {
                        // Login failed, show appropriate error message
                        when {
                            task.exception?.message?.contains("no user record") == true -> {
                                showMessage("No account found with this email")
                            }
                            task.exception?.message?.contains("password is invalid") == true -> {
                                showMessage("Invalid password")
                            }
                            else -> {
                                showMessage("Authentication failed: ${task.exception?.message}")
                            }
                        }
                    }
                }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Helper function to show a toast message
    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
