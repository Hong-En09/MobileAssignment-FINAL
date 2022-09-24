package com.example.mobileassignment.ui.CustomerRequestList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileassignment.databinding.FragmentRequestlistBinding

class RequestListFragment: Fragment() {

    private var _binding: FragmentRequestlistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRequestlistBinding.inflate(inflater, container, false)
        return binding.root

    }




    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

    }
}