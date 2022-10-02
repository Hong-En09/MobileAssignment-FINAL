package com.example.mobileassignment.ui.CustomerFolder

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.mobileassignment.Entity.AcceptedRequestList
import com.example.mobileassignment.Entity.ProductList
import com.example.mobileassignment.R
import com.example.mobileassignment.databinding.ActivityAddNewrequestBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_newrequest.*
import java.util.*


class AddNewRequestActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddNewrequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_newrequest)

        var modalItems: ProductList = intent.getSerializableExtra("data") as ProductList

        Log.e("name", modalItems.name.toString())

        productName2.text = modalItems.name
        viewImage2.setImageResource(modalItems.image!!)
        val sharedPreferences =
            getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)



        confirmRequestButton.setOnClickListener {
            val product = modalItems.name.toString()
            val quantity = quantityValue.text.toString()
            val price = priceValue.text.toString()
            val username = sharedPreferences?.getString("username", null).toString()
            val address = sharedPreferences?.getString("address", null).toString()
            val uniqueID = UUID.randomUUID().toString()
            val status = "Pending"
            val dealer = ""
            val storageRef = FirebaseStorage.getInstance().reference
            val phone = sharedPreferences?.getString("phone", null).toString()
            val photoURL = storageRef.child(product + ".png").toString()





            if (quantity.isNotEmpty() && price.isNotEmpty()) {
                val newRequest = AcceptedRequestList(uniqueID, username, product, quantity, price, status, photoURL, dealer,address,phone)
                val database: DatabaseReference = Firebase.database.getReference("requestList")





                //database.child(newRequest.username).child(newRequest.uniqueID).setValue(newRequest)
                database.child(newRequest.uniqueID).setValue(newRequest)
                Toast.makeText(this, "Request is successful created", Toast.LENGTH_SHORT).show()
                finish();
            } else {
                Toast.makeText(this, "Request Fail to Created", Toast.LENGTH_SHORT).show()
            }
        }

    }

}