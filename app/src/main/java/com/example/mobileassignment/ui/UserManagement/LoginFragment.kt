package com.example.mobileassignment.ui.UserManagement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileassignment.databinding.FragmentLoginBinding
import com.example.mobileassignment.ui.Admin.AdminActivity
import com.example.mobileassignment.ui.CustomerFolder.CustomerActivity
import com.example.mobileassignment.ui.Homepage.FarmerActivity
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
            val password : String = binding.editTextTextPassword.text.toString()
            if(username.isNotEmpty()){
                if(username == "admin123" && password == "admin123"){
                    startActivity(Intent(context, AdminActivity::class.java))
                }
                readData(username,password)

            }else{
                Toast.makeText(context, "Username or password cannot be empty", Toast.LENGTH_SHORT).show()
            }

        }
        //binding.buttonRegister.setOnClickListener {
        //findNavController().navigate(R.id.RegisterFragment)
        //}
    }

    private fun readData(username: String, password: String) {
        var role = ""
        var email = ""
        var phone = ""
        var address = ""
        var url = ""
        var pass = ""
        val databaseRef = FirebaseDatabase.getInstance().reference.child("user")
            .orderByChild("username").equalTo(username)
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children){
                    pass = ds.child("password").getValue(String::class.java).toString()


                        role = ds.child("role").getValue(String::class.java).toString()
                        email = ds.child("email").getValue(String::class.java).toString()
                        phone = ds.child("phoneNum").getValue(String::class.java).toString()
                        address = ds.child("address").getValue(String::class.java).toString()
                        url = ds.child("photoUrl").getValue(String::class.java).toString()



                }
                if(password == pass){
                    val sharedPreferences = activity?.getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
                    with (sharedPreferences!!.edit()) {
                        putString("username", username)
                        putString("email", email)
                        putString("role", role)
                        putString("phone", phone)
                        putString("address", address)
                        putString("userURL", url)
                        putString("pass", pass)
                        apply()
                    }

                    if(role == "Farmer"){
                        startActivity(Intent(context, FarmerActivity::class.java))
                    }else{
                        startActivity(Intent(context, CustomerActivity::class.java))
                    }
                }else{
                    Toast.makeText(context, "Invalid username or password.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}