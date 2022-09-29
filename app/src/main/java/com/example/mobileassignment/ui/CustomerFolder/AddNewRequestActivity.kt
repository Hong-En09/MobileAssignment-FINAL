package com.example.mobileassignment.ui.CustomerFolder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.mobileassignment.Entity.ProductList
import com.example.mobileassignment.R
import com.example.mobileassignment.databinding.ActivityAddNewrequestBinding
import kotlinx.android.synthetic.main.activity_add_newrequest.*

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

    }

}