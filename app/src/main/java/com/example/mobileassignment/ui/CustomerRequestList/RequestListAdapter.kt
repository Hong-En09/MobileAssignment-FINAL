package com.example.mobileassignment.ui.CustomerRequestList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.RequestList
import com.example.mobileassignment.R

class RequestListAdapter : RecyclerView.Adapter<RequestListAdapter.ViewHolder>() {

    private var dataSet = emptyList<RequestList>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record
        val textName: TextView = view.findViewById(R.id.textViewItem)
        val textPhone: TextView = view.findViewById<TextView>(R.id.textViewQuantity)

        init {
            view.setOnClickListener{
                //todo: handle click event
            }
        }
    }

    internal fun setReuestList(requestList: List<RequestList>){
        dataSet = requestList
        notifyDataSetChanged() //refresh the RecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.requestlist_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val requestlist = dataSet[position]
        holder.textName.text = requestlist.item
        holder.textPhone.text = requestlist.quantity
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}