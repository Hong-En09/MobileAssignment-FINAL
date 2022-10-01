package com.example.mobileassignment.ui.CustomerRequestList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.databinding.FragmentRequestlistBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

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

        val databaseRef = FirebaseDatabase.getInstance().reference.child("requestList").orderByChild("status").equalTo("Pending")
        val requestAdapter = RequestListAdapter()
        var requestList = arrayListOf<AcceptedRequestList>()
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children){
                    val data = ds.getValue<AcceptedRequestList>()
                    if (data != null) {
                        requestList.add(data)
                    }

                }
                requestAdapter.setRequestList(requestList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        binding.recycleViewRequestList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.recycleViewRequestList.adapter = requestAdapter
    }
}