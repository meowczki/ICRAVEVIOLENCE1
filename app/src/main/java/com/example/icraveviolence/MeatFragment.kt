package com.example.icraveviolence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.icraveviolence.databinding.FragmentLoginBinding
import com.example.icraveviolence.databinding.FragmentMeatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MeatFragment : Fragment() {

    private lateinit var binding: FragmentMeatBinding;
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            FragmentMeatBinding.bind(inflater.inflate(R.layout.fragment_meat, container, false));
        val view = binding.root;

        binding.next.setOnClickListener {

            goToBalanced()
        }


        binding.back.setOnClickListener {
            goToWeight()
        }

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateSpinners(view)
    }

    private fun populateSpinners(view: View) {
        // ...
        database = Firebase.database.reference
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val spinnerData =
            arrayOf("Spinner Item 1", "Spinner Item 2", "Spinner Item 3", "Spinner Item 4")
        spinner.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerData
        )
    }

    private fun goToWeight() {
        //wroc do fragent weight
    }

    private fun goToBalanced() {
        //idz do fragmentu balnced suple
//        val amountTv: EditText = view!!.findViewById(R.id.editTextAmount)
        //dodaj do amount id
//        val amount = 0
//        val action = MeatFragmentDirections.actionMeatFragmentToBalancedSupplementsFragment(amount)
//        NavHostFragment.findNavController(requireParentFragment()).navigate(action);
    }
}