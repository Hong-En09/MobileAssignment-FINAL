package com.example.mobileassignment.ui.FarmerFolder

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_contract_detail.*
import kotlinx.android.synthetic.main.fragment_contract_detail.*
import kotlinx.android.synthetic.main.fragment_contract_detail.CustomerName
import kotlinx.android.synthetic.main.fragment_contract_detail.QuantityORWeight
import kotlinx.android.synthetic.main.fragment_contract_detail.textViewItemSelected
import kotlinx.android.synthetic.main.nav_header_farmer.view.*
import java.util.*

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
        var price = ""
        var uniqueID = ""
        var address = ""
        var phone = ""
        var status = "Processing"
        var dealer = sharedPreferences!!.getString("username", null).toString()
        var databaseRef = FirebaseDatabase.getInstance().reference.child("requestList")
            .orderByChild("uniqueID").equalTo(uniqueIDRequest)
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    product = ds.child("product").getValue(String::class.java).toString()
                    photoURL = ds.child("photoURL").getValue(String::class.java).toString()
                    username = ds.child("username").getValue(String::class.java).toString()
                    quantity = ds.child("quantity").getValue(String::class.java).toString()
                    price = ds.child("price").getValue(String::class.java).toString()
                    uniqueID = ds.child("uniqueID").getValue(String::class.java).toString()
                    address = ds.child("address").getValue(String::class.java).toString()
                    phone = ds.child("phone").getValue(String::class.java).toString()
                }
                val storageRef = FirebaseStorage.getInstance().reference

                val photoRef = storageRef.child(product + ".png")
                val ONE_MEGABYTE = (1024 * 1024).toLong()
                photoRef.getBytes(ONE_MEGABYTE)
                    .addOnSuccessListener { bytes ->
                        val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        contractImageView.setImageBitmap(bmp)
                    }.addOnFailureListener(OnFailureListener {

                    })

                textViewItemSelected.text = product
                CustomerName.text = username
                QuantityORWeight.text = quantity
                priceContract.text = price
                addressContract.text = address
                phoneContract.text = phone



            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        val button = findViewById<Button>(R.id.buttonAcceptRequest)
        button.setOnClickListener{


            val database: DatabaseReference = Firebase.database.getReference("requestList")

            database.child(uniqueIDRequest).child("dealer").setValue(dealer)
            database.child(uniqueIDRequest).child("status").setValue(status)
            Toast.makeText(this, "Request is successful created", Toast.LENGTH_SHORT).show()
            finish();
        }



    }


}
