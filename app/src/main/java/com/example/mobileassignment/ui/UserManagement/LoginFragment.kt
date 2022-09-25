package com.example.mobileassignment.ui.UserManagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
            val password : String = binding.editTextTextPassword.text.toString()
            if(username.isNotEmpty()){

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
        val databaseRef = FirebaseDatabase.getInstance().reference.child("user").orderByChild("username").equalTo(username)
        databaseRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children){
                    val pass = ds.child("password").getValue(String::class.java)
                    if(password == pass){
                        Toast.makeText(context, "Yesssss", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

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