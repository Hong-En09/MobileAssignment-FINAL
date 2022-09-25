package com.example.mobileassignment.ui.UserManagement

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobileassignment.Entity.User
import com.example.mobileassignment.R
import com.example.mobileassignment.databinding.FragmentLoginBinding
import com.google.firebase.database.*

class LoginFragment: Fragment() {


    private lateinit var database : DatabaseReference
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLoginBtn.setOnClickListener {
            val username : String = binding.editTextUsername.text.toString()
            if(username.isNotEmpty()){

                readData(username)

            }else{
                Toast.makeText(context, "Username or password cannot be empty", Toast.LENGTH_SHORT).show()
            }

        }
        //binding.buttonRegister.setOnClickListener {
        //findNavController().navigate(R.id.RegisterFragment)
        //}
    }

    private fun readData(username: String) {
        val databaseRef = FirebaseDatabase.getInstance().reference
        val  classArrayList = ArrayList<User>()
        databaseRef.child("user").get().addOnSuccessListener {babi->
            Log.i("babi firebase", "Got value ${babi.value}")

        }.addOnFailureListener {babi->
            Log.e("babi firebase", "Error getting data", babi)
        }

        /*database.child("username").get().addOnSuccessListener {
            if(it.exists()){
                for(children in it.children){
                    if
                }
                val accPassword = it.child("password").value
                if(accPassword == binding.editTextTextPassword.text.toString()){
                    findNavController().navigate(R.id.nav_farmerHomepage)
                }
            }else{
                Toast.makeText(context, "Username or password wrong.", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(context, "Account not exist.", Toast.LENGTH_SHORT).show()
        }*/
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}