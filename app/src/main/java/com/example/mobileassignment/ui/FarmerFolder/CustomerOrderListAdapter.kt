package com.example.mobileassignment.ui.FarmerFolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.Entity.CustomerOrderList
import com.example.mobileassignment.R

class CustomerOrderListAdapter : RecyclerView.Adapter<CustomerOrderListAdapter.ViewHolder>() {

    private var dataSet = emptyList<AcceptedRequestList>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record
        val textName: TextView = view.findViewById(R.id.acceptedName)
        val textProduct: TextView = view.findViewById(R.id.acceptedProduct)
        val textPrice: TextView = view.findViewById(R.id.acceptedPrice)
        val textQuantity: TextView = view.findViewById(R.id.acceptedQuantity)

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

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}