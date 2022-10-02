package com.example.mobileassignment.ui.CustomerRequestList

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.R
import com.example.mobileassignment.ui.FarmerFolder.ContractDetailActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.FirebaseStorage


class RequestListAdapter : RecyclerView.Adapter<RequestListAdapter.ViewHolder>() {

    private var dataSet = emptyList<AcceptedRequestList>()
    private lateinit var uniqueID: String

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //view (parameter) refers to the layout hosting each record
        val imageItem: ImageView = view.findViewById(R.id.imageViewProduct)
        val textItem: TextView = view.findViewById(R.id.textViewItem)
        val textQuantity: TextView = view.findViewById(R.id.textViewQuantity)
        val imageButtonChoose: Button = view.findViewById(R.id.imageButtonChoose)
        val preferences = view.context.getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)!!


    }

    internal fun setRequestList(requestList: List<AcceptedRequestList>){
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
        val product = requestlist.product
        val storageRef = FirebaseStorage.getInstance().reference

        val photoRef = storageRef.child(product + ".png")
        val ONE_MEGABYTE = (1024 * 1024).toLong()
        photoRef.getBytes(ONE_MEGABYTE)
            .addOnSuccessListener { bytes ->
                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                holder.imageItem.setImageBitmap(bmp)
            }.addOnFailureListener(OnFailureListener {

            })



        holder.textItem.text = requestlist.product
        holder.textQuantity.text = requestlist.quantity

        holder.imageButtonChoose.setOnClickListener {


            holder.preferences.edit().putString("uniqueID",requestlist.uniqueID).apply()
            //it.findNavController().navigate(R.id.action_nav_requestList_to_contractDetailFragment)

            //val intent = Intent(holder.itemView.context, ContractDetailActivity::class.java)
            //holder.itemView.context.startActivity(intent)

            val context = holder.itemView.context
            val intent = Intent( context, ContractDetailActivity::class.java)
            //context.startActivity(intent)

            holder.itemView.getContext().startActivity(intent);
        }
    }

    private fun getData(uniqueID:String):String {

        return uniqueID
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}