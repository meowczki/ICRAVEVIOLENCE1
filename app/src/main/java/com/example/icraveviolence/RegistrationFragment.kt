package com.example.icraveviolence

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.icraveviolence.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegistrationFragment : Fragment() {
//If view binding is enabled for a module, a binding class is
// generated for each XML layout file that the module contains.
    private lateinit var binding: FragmentRegistrationBinding;
    private lateinit var mAuth: FirebaseAuth;
    private lateinit var reference: DatabaseReference;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentRegistrationBinding.bind(inflater.inflate(R.layout.fragment_registration, container, false));
        val view = binding.root;

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().reference.child("Users");

        binding.btnReg.setOnClickListener {
            register()
        }
        return view;
    }

    private fun register() {
        val fname = binding.txtRegFname.text.toString().trim();
        val lname = binding.txtRegLname.text.toString().trim();
        val email = binding.txtRegEmail.text.toString().trim();
        val password = binding.txtRegPassword.text.toString().trim();

        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this.requireContext(), "Fill in all the details", Toast.LENGTH_SHORT).show()
        } else {
            val name = "$fname $lname";
            // dodaj enable auth w konsoli
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        val action = RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
                        NavHostFragment.findNavController(requireParentFragment()).navigate(action)
                        //println("poszlo")
                    } else {
                        //println(task.exception?.message)
                        Toast.makeText(this.requireContext(), "Unsuccessful", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


}