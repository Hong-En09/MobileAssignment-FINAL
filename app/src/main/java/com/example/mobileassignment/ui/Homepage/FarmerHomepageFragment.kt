package com.example.mobileassignment.ui.Homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobileassignment.R
import com.example.mobileassignment.databinding.FragmentFarmerHomepageBinding
import com.google.android.material.navigation.NavigationView

class FarmerHomepageFragment: Fragment() {

    private var _binding: FragmentFarmerHomepageBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFarmerHomepageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationView.OnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_productList -> {
                    findNavController().navigate(R.id.nav_productList)
                    true
                }
                else -> false
            }

        }

        //findNavController().navigate(R.id.action_nav_farmerHomepage_to_nav_productList)

        //binding.buttonRegister.setOnClickListener {
        //findNavController().navigate(R.id.RegisterFragment)
        //}
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}


