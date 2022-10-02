package com.example.mobileassignment.ui.FarmerFolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.Entity.CustomerOrderList
import com.example.mobileassignment.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CustomerOrderListAdapter : RecyclerView.Adapter<CustomerOrderListAdapter.ViewHolder>() {

    private var dataSet = emptyList<AcceptedRequestList>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record
        val textName: TextView = view.findViewById(R.id.usernameAccepted)
        val textProduct: TextView = view.findViewById(R.id.productAccepted)
        val textPrice: TextView = view.findViewById(R.id.priceAccepted)
        val textQuantity: TextView = view.findViewById(R.id.quantityAccepted)
        val textStatus: TextView = view.findViewById(R.id.statusAccepted)
        val textAddress: TextView = view.findViewById(R.id.addressAccepted)
        val updateButton: Button = view.findViewById(R.id.buttonArrived)

        init {
            view.setOnClickListener{
                //todo: handle click event
            }
        }
    }

    internal fun setCustomerOrderList(orderList: List<AcceptedRequestList>){
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
        holder.updateButton.setOnClickListener{
            val database: DatabaseReference = Firebase.database.getReference("requestList")


            database.child(orderList.uniqueID).child("status").setValue(status)
        }


    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}