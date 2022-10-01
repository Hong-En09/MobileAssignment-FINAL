package com.example.mobileassignment.ui.CustomerFolder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.Entity.RequestList
import com.example.mobileassignment.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AcceptedRequestListAdapter: RecyclerView.Adapter<AcceptedRequestListAdapter.ViewHolder>() {

    private var dataSet = emptyList<AcceptedRequestList>()



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record

        val textName: TextView = view.findViewById(R.id.acceptedName)
        val textProduct: TextView = view.findViewById(R.id.acceptedProduct)
        val textPrice: TextView = view.findViewById(R.id.acceptedPrice)
        val textQuantity: TextView = view.findViewById(R.id.acceptedQuantity)
        val textStatus: TextView = view.findViewById(R.id.acceptedStatus)

        init {
            view.setOnClickListener{
                //todo: handle click event
            }
        }
    }
   // internal fun setAcceptedRequestList(acceptedRequestList: List<AcceptedRequestList>){
   //     dataSet = acceptedRequestList
   //     notifyDataSetChanged() //refresh the RecyclerView
    //}
    internal fun setAcceptedRequestList(acceptedRequestList: List<AcceptedRequestList>){
        dataSet = acceptedRequestList
        notifyDataSetChanged() //refresh the RecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.accepted_requestlist_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderList = dataSet[position]
        holder.textName.text = orderList.username
        holder.textProduct.text = orderList.product
        holder.textQuantity.text = orderList.quantity
        holder.textPrice.text = orderList.price
        holder.textStatus.text = orderList.status



    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}