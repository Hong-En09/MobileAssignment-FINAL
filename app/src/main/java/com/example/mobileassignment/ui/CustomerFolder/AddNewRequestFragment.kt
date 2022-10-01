package com.example.mobileassignment.ui.CustomerFolder

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileassignment.Entity.ProductList
import com.example.mobileassignment.Entity.RequestList
import com.example.mobileassignment.databinding.FragmentAddNewrequestBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class AddNewRequestFragment: Fragment() {

    private var _binding: FragmentAddNewrequestBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNewrequestBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //var modalItems: ProductList = intent.getSerializable
        var modelItem: ProductList = arguments?.getSerializable("amount") as ProductList

        //Log.e("modal", modelItem.name.toString())
        //Log.e("modal", modelItem.image.toString())
        //Log.e("babiHongEn", resources.getDrawable(R.drawable.carrot).toString())
        //val dr: Drawable = resources.getDrawable(modelItem.image!!)

        val sharedPreferences =
            activity?.getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)


        var mDrawableName = "carrot"
        val resID = getResources().getIdentifier(mDrawableName, "drawable", context?.packageName)
        val logoDrawable: Drawable = getResources().getDrawable(resID)
        binding.viewImage.setImageDrawable(logoDrawable)



        binding.confirmRequestButton.setOnClickListener {
            val product = binding.productName.text.toString()
            val quantity = binding.quantityValue.text.toString()
            val price = binding.priceValue.text.toString()
            val username = sharedPreferences?.getString("username", null).toString()
            val uniqueID = UUID.randomUUID().toString()
            if (quantity.isNotEmpty() && price.isNotEmpty()) {
                val newRequest = RequestList(uniqueID, username, product, quantity, price)
                val database: DatabaseReference = Firebase.database.getReference("requestList")

                database.child(uniqueID).setValue(newRequest)

            } else {
                Toast.makeText(context, "Request Fail to Created", Toast.LENGTH_SHORT).show()
            }
        }


    }

        override fun onResume() {
            super.onResume()
            (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        }

}

