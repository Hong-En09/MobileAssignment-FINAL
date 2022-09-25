package com.example.mobileassignment.ui.FarmerFolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.CustomerOrderList
import com.example.mobileassignment.R

class CustomerOrderListAdapter : RecyclerView.Adapter<CustomerOrderListAdapter.ViewHolder>() {

    private var dataSet = emptyList<CustomerOrderList>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record
        val textName: TextView = view.findViewById(R.id.name)
        val textAddress: TextView = view.findViewById<TextView>(R.id.address)
        val textPhoneNum: TextView = view.findViewById(R.id.phoneNumber)
        val textStatus: TextView = view.findViewById(R.id.status)

        init {
            view.setOnClickListener{
                //todo: handle click event
            }
        }
    }

    internal fun setCustomerOrderList(orderList: List<CustomerOrderList>){
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
        holder.textName.text = orderList.name
        holder.textAddress.text = orderList.address
        holder.textPhoneNum.text = orderList.phoneNum
        holder.textStatus.text = orderList.status

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}