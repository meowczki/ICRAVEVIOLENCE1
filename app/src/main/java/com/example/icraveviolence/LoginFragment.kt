package com.example.icraveviolence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.icraveviolence.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding;
    private lateinit var mAuth: FirebaseAuth;
    private lateinit var progressBar: ProgressBar;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentLoginBinding.bind(inflater.inflate(R.layout.fragment_login, container, false));
        val view = binding.root;
        mAuth = FirebaseAuth.getInstance();

        binding.btn.setOnClickListener {
            login()
        }
        binding.btnDemo.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment();
            NavHostFragment.findNavController(requireParentFragment()).navigate(action);
        }

        progressBar = binding.progressbar;

        binding.tvRegistration.setOnClickListener {
            register()
        }

        return view;
    }

    private fun register() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
        NavHostFragment.findNavController(requireParentFragment()).navigate(action)
    }

    private fun login() {
        val email = binding.txtLoginEmail.text.toString().trim();
        val password = binding.txtLoginPassword.text.toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this.context, "Empty fields", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.visibility = View.VISIBLE
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    //w navigation
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment();
                    NavHostFragment.findNavController(requireParentFragment()).navigate(action);
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this.context, "Error logging in", Toast.LENGTH_SHORT).show()
                }
            }
    }


}