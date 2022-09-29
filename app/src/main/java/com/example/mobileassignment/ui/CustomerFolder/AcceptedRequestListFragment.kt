package com.example.mobileassignment.ui.CustomerFolder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileassignment.databinding.FragmentAcceptedRequestlistBinding

class AcceptedRequestListFragment: Fragment() {

    private var _binding: FragmentAcceptedRequestlistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAcceptedRequestlistBinding.inflate(inflater, container, false)

        return binding.root


    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        val rvAdapter = AcceptedRequestListAdapter()

        //rvAdapter.setData(AcceptedRequestList)
        binding.recycleAcceptedRequestList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.recycleAcceptedRequestList.adapter = rvAdapter
    }
}