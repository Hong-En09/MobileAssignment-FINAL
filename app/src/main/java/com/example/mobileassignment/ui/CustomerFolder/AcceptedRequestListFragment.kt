package com.example.mobileassignment.ui.CustomerFolder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.databinding.FragmentAcceptedRequestlistBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.accepted_requestlist_layout.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()




        val sharedPreferences = context?.getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
        val username = sharedPreferences!!.getString("username", null)
        val databaseRef = FirebaseDatabase.getInstance().reference.child("requestList").orderByChild("username")
            .equalTo(username)
        val requestAdapter = AcceptedRequestListAdapter()
        var requestList = arrayListOf<AcceptedRequestList>()
        var uniqueID = ""
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children){
                    val data = ds.getValue<AcceptedRequestList>()
                    uniqueID = ds.child("uniqueID").getValue(String::class.java).toString()
                    if (data != null) {
                        requestList.add(data)
                    }

                }

                requestAdapter.setAcceptedRequestList(requestList)


            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        binding.recycleAcceptedRequestList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.recycleAcceptedRequestList.adapter = requestAdapter
    }
}