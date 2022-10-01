package com.example.mobileassignment.ui.FarmerFolder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.databinding.FragmentCustomerOrderlistBinding
import com.example.mobileassignment.ui.CustomerFolder.AcceptedRequestListAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class CustomerOrderListFragment: Fragment() {

    private var _binding: FragmentCustomerOrderlistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCustomerOrderlistBinding.inflate(inflater, container, false)

        return binding.root


    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        val sharedPreferences = context?.getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
        val username = sharedPreferences!!.getString("username", null)
        val databaseRef = FirebaseDatabase.getInstance().reference.child("requestList")
            .orderByChild("dealer")
            .equalTo(username)
        val customerOrderAdapter = CustomerOrderListAdapter()
        var orderList = arrayListOf<AcceptedRequestList>()
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children){
                    val data = ds.getValue<AcceptedRequestList>()
                    if (data != null) {
                        orderList.add(data)
                    }

                }
                customerOrderAdapter.setCustomerOrderList(orderList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        binding.recycleViewOrderList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.recycleViewOrderList.adapter = customerOrderAdapter
    }
}