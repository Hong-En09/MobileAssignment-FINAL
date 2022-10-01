package com.example.mobileassignment.ui.FarmerFolder

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_contract_detail.*
import kotlinx.android.synthetic.main.nav_header_farmer.view.*

class ContractDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract_detail)

        val sharedPreferences =
            getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)



        val uniqueIDRequest = sharedPreferences!!.getString("uniqueID", null).toString()
        var product = ""
        var photoURL = ""
        var username = ""
        var quantity = ""
        var databaseRef = FirebaseDatabase.getInstance().reference.child("requestList")
            .orderByChild("uniqueID").equalTo(uniqueIDRequest)
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    product = ds.child("product").getValue(String::class.java).toString()
                    photoURL = ds.child("photoURL").getValue(String::class.java).toString()
                    username = ds.child("username").getValue(String::class.java).toString()
                    quantity = ds.child("quantity").getValue(String::class.java).toString()
                    //val location = ds.child("location").getValue(String::class.java)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        val storageRef = FirebaseStorage.getInstance().reference

        val photoRef = storageRef.child(product + ".png")
        val ONE_MEGABYTE = (1024 * 1024).toLong()
        photoRef.getBytes(ONE_MEGABYTE)
            .addOnSuccessListener { bytes ->
                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                contractImage.imageView.setImageBitmap(bmp)
            }.addOnFailureListener(OnFailureListener {

            })

        textViewItemSelected.text = product
        CustomerName.text = username
        QuantityORWeight.text = quantity

    }

}