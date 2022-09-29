package com.example.mobileassignment.ui.CustomerFolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.R

class AcceptedRequestListAdapter: RecyclerView.Adapter<AcceptedRequestListAdapter.ViewHolder>() {

    //private var dataSet = emptyList<AcceptedRequestList>()
    private var acceptedRequest = AcceptedRequestList("aaa","carrot", "0123456789", "Pending")
    private var acceptedRequest2 = AcceptedRequestList("aaa","carrot", "0123456789", "Pending")
    private var acceptedRequest3 = AcceptedRequestList("aaa","carrot", "0123456789", "Pending")
    private var acceptedRequest4 = AcceptedRequestList("aaa","carrot", "0123456789", "Pending")
    private var acceptedRequest5 = AcceptedRequestList("aaa","carrot", "0123456789", "Pending")
    private var acceptedRequest6 = AcceptedRequestList("aaa","carrot", "0123456789", "Pending")
    private var dataSet = arrayListOf<AcceptedRequestList>(acceptedRequest, acceptedRequest2, acceptedRequest3, acceptedRequest4,acceptedRequest5,acceptedRequest6)

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record
        val textName: TextView = view.findViewById(R.id.acceptedName)
        val textProduct: TextView = view.findViewById(R.id.acceptedProduct)
        val textPhoneNum: TextView = view.findViewById(R.id.acceptedPhoneNum)
        val textStatus: TextView = view.findViewById(R.id.acceptedStatus)

        init {
            view.setOnClickListener{
                //todo: handle click event
            }
        }
    }

    internal fun setAcceptedRequestList(acceptedRequestList: List<AcceptedRequestList>){
        //dataSet = acceptedRequestList
        notifyDataSetChanged() //refresh the RecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.accepted_requestlist_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderList = dataSet[position]
        holder.textName.text = orderList.name
        holder.textProduct.text = orderList.product
        holder.textPhoneNum.text = orderList.phoneNum
        holder.textStatus.text = orderList.status

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}