package com.example.mobileassignment.ui.CustomerFolder

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileassignment.Entity.ProductList
import com.example.mobileassignment.databinding.FragmentAddNewrequestBinding

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

        var mDrawableName = "carrot";
        val resID = getResources().getIdentifier(mDrawableName , "drawable", context?.packageName);
        val logoDrawable: Drawable = getResources().getDrawable(resID);
        binding.viewImage.setImageDrawable(logoDrawable)


        //binding.viewImage.setImageResource(modelItem.image!!)
        //binding.viewImage.setImageResource(R.drawable.)
        //val Item = requireActivity().intent.extras!!.getSerializable("data")
        Toast.makeText(context, "Profile Saved", Toast.LENGTH_SHORT).show()


    }


    private fun showData(product: ProductList){

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

    }
}

