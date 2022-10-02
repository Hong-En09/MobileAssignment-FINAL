package com.example.mobileassignment.ui.FarmerFolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.Entity.CustomerOrderList
import com.example.mobileassignment.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CustomerOrderListAdapter : RecyclerView.Adapter<CustomerOrderListAdapter.ViewHolder>() {

    private var dataSet = mutableListOf<AcceptedRequestList>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record
        val textName: TextView = view.findViewById(R.id.usernameAccepted)
        val textProduct: TextView = view.findViewById(R.id.productAccepted)
        val textPrice: TextView = view.findViewById(R.id.priceAccepted)
        val textQuantity: TextView = view.findViewById(R.id.quantityAccepted)
        val textStatus: TextView = view.findViewById(R.id.statusAccepted)
        val updateButton: Button = view.findViewById(R.id.buttonArrived)
        val textAddress: TextView = view.findViewById(R.id.addressAccepted)

        init {
            view.setOnClickListener{
                //todo: handle click event
            }
        }
    }

    internal fun setCustomerOrderList(orderList: MutableList<AcceptedRequestList>){
        dataSet = orderList
        notifyDataSetChanged() //refresh the RecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.orderlist_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderList = dataSet[position]
        holder.textName.text = orderList.username
        holder.textProduct.text = orderList.product
        holder.textQuantity.text = orderList.quantity
        holder.textPrice.text = orderList.price
        holder.textStatus.text = orderList.status
        holder.textAddress.text = orderList.address
        val status = "Arrived"
        if(holder.textStatus.text != "Arrived"){
            holder.updateButton.isVisible = true
        }
        holder.updateButton.setOnClickListener{
            val databaseContract: DatabaseReference = Firebase.database.getReference("requestList")
            databaseContract.child(orderList.uniqueID).child("status").setValue(status)
            dataSet.clear()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}