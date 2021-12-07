package com.example.icraveviolence

import ProductViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.icraveviolence.databinding.FragmentMultiBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class MultiFragment : Fragment() {
    private lateinit var recipeList: MutableList<Recipe>
    private lateinit var productList: List<Product>
    private lateinit var viewModel: ProductViewModel
    private lateinit var binding: FragmentMultiBinding;
    //private lateinit var mAuth: FirebaseAuth;
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        database =
            Firebase.database.getReferenceFromUrl("https://blagam-75c70-default-rtdb.europe-west1.firebasedatabase.app")
        binding =
            FragmentMultiBinding.bind(inflater.inflate(R.layout.fragment_multi, container, false))
        val view = binding.root


        binding.next.setOnClickListener {
            nextView()
        }
        binding.back.setOnClickListener {
            previousView()
        }
        binding.add.setOnClickListener {

        }
        viewModel.fetchAllProducts()
        productList = listOf<Product>()
        recipeList = mutableListOf<Recipe>()
        viewModel.products.observe(viewLifecycleOwner, Observer { it ->
            productList = it
            populateSpinners()
        })

        return view;
    }

    private fun populateSpinners() {
        binding.meatSpinner.adapter = getAdapter(productList.filter { it.type == "mieso" })
        binding.boneSpinner.adapter = getAdapter(productList.filter { it.type == "kosci" })
        binding.stomachSpinner.adapter = getAdapter(productList.filter { it.type == "zoladek" })
        binding.heartSpinner.adapter = getAdapter(productList.filter { it.type == "serce" })
        binding.offalSpinner.adapter = getAdapter(productList.filter { it.type == "podroby" })
        binding.balancedSpinner.adapter = getAdapter(productList.filter { it.type == "preparaty" })
        binding.vitaSpinner.adapter = getAdapter(productList.filter { it.type == "wita" })
        binding.fishSpinner.adapter = getAdapter(productList.filter { it.type == "ryba" })
        binding.vitdSpinner.adapter = getAdapter(productList.filter { it.type == "witd" })
        binding.iodineSpinner.adapter = getAdapter(productList.filter { it.type == "jod" })
        binding.otherSpinner.adapter = getAdapter(productList.filter { it.type == "inne" })
        binding.vitbSpinner.adapter = getAdapter(productList.filter { it.type == "witb" })
        binding.ironSpinner.adapter = getAdapter(productList.filter { it.type == "zelazo" })
        binding.viteSpinner.adapter = getAdapter(productList.filter { it.type == "wite" })
        binding.fatSpinner.adapter = getAdapter(productList.filter { it.type == "tluszcz" })
        binding.oilSpinner.adapter = getAdapter(productList.filter { it.type == "olej" })
        binding.calcPhosphSpinner.adapter =
            getAdapter(productList.filter { it.type == "wapn_fosfor" })
        binding.calcSpinner.adapter = getAdapter(productList.filter { it.type == "wapn" })
        binding.sodiumSpinner.adapter = getAdapter(productList.filter { it.type == "sod" })
        binding.vegSpinner.adapter = getAdapter(productList.filter { it.type == "warzywa" })
        var spinners = binding.root.getViewsByType(Spinner::class.java)


    }

    private fun getAdapter(subList: List<Product>): CustomSpinnerAdapter {
        val aa = CustomSpinnerAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            subList
        )
        return aa
    }

    fun <T : View> ViewGroup.getViewsByType(tClass: Class<T>): List<T> {
        return mutableListOf<T?>().apply {
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                (child as? ViewGroup)?.let {
                    addAll(child.getViewsByType(tClass))
                }
                if (tClass.isInstance(child))
                    add(tClass.cast(child))
            }
        }.filterNotNull()
    }


    private fun previousView() {
        var current_view_id =
            getCurrentView()
        when (current_view_id) {
            binding.balancedLayout.id -> backToMeat()
            binding.naturalLayout.id -> backToBalanced()
            binding.otherLayout.id -> backToNatural()
            binding.fatLayout.id -> backToOther()
            binding.calcLayout.id -> backToFat()
            binding.taurineLayout.id -> backToCalc()
            else -> {
                //
                println("hmmmm???")
            }
        }
    }

    private fun backToNatural() {
        binding.naturalLayout.toggleVisibility()
        binding.otherLayout.toggleVisibility()
    }

    private fun backToFat() {
        binding.calcLayout.toggleVisibility()
        binding.fatLayout.toggleVisibility()
    }

    private fun backToCalc() {
        binding.calcLayout.toggleVisibility()
        binding.taurineLayout.toggleVisibility()
    }

    private fun backToOther() {
        binding.fatLayout.toggleVisibility()
        binding.otherLayout.toggleVisibility()
    }

    private fun backToBalanced() {
        binding.balancedLayout.toggleVisibility()
        binding.naturalLayout.toggleVisibility()
    }

    private fun backToMeat() {
        binding.meatLayout.toggleVisibility()
        binding.balancedLayout.toggleVisibility()
        binding.back.toggleVisibility()
    }


    private fun nextView() {
        var current_view_id =
            getCurrentView()

        when (current_view_id) {
            binding.meatLayout.id -> goToMeat()
            binding.balancedLayout.id -> goToBalanced()
            binding.naturalLayout.id -> goToNatural()
            binding.otherLayout.id -> goToOther()
            binding.fatLayout.id -> goToFat()
            binding.calcLayout.id -> goToCalc()
            binding.taurineLayout.id -> goToTaurine()
            else -> {
                //
                println("hmmmm???")
            }
        }
    }

    private fun getCurrentView(): Int {
        var current_view_id =
            binding.root.getAllViews().filter { it.visibility == View.VISIBLE && it.tag == "view" }
                .get(0).id
        return current_view_id
    }

    private fun goToNatural() {
        binding.naturalLayout.toggleVisibility()
        binding.otherLayout.toggleVisibility()

    }

    private fun goToTaurine() {
        print("done")
    }

    private fun goToCalc() {
        binding.calcLayout.toggleVisibility()
        binding.taurineLayout.toggleVisibility()
    }

    private fun goToFat() {
        binding.fatLayout.toggleVisibility()
        binding.calcLayout.toggleVisibility()
    }

    private fun goToOther() {
        binding.otherLayout.toggleVisibility()
        binding.fatLayout.toggleVisibility()
    }

    private fun goToBalanced() {
        binding.balancedLayout.toggleVisibility()
        binding.naturalLayout.toggleVisibility()

    }

    private fun goToMeat() {
        binding.meatLayout.toggleVisibility()
        binding.balancedLayout.toggleVisibility()
        binding.back.toggleVisibility()
        addMeat()


    }

    private fun addMeat() {
        val productAdded = binding.meatSpinner.selectedItem as Product
        println(productAdded.productId)
    }
    private fun  addProduct(){
        val productAdded = binding.meatSpinner.selectedItem as Product
        var spinners = binding.root.getViewsByType(Spinner::class.java)
    }

    fun View.toggleVisibility() {
        if (visibility == View.VISIBLE) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
        }
    }

    private fun View.getAllViews(): List<View> {
        if (this !is ViewGroup || childCount == 0) return listOf(this)

        return children
            .toList()
            .flatMap { it.getAllViews() }
            .plus(this as View)
    }
}