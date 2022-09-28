package com.example.mobileassignment.ui.CustomerFolder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.Entity.ProductList
import com.example.mobileassignment.R
import kotlinx.android.synthetic.main.activity_customer.*

class CustomerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        var modalItems: ProductList = intent.getSerializableExtra("data") as ProductList

        Log.e("name", modalItems.name.toString())

        productName2.text = modalItems.name
        viewImage2.setImageResource(modalItems.image!!)
    }
}