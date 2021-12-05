package com.example.icraveviolence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.icraveviolence.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding;
    private lateinit var mAuth: FirebaseAuth;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        val view = binding.root

        mAuth = FirebaseAuth.getInstance();

        binding.tvEmail.text = mAuth.currentUser!!.email

        return view;
    }
}