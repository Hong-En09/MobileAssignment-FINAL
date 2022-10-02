package com.example.mobileassignment.ui.UserManagement


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mobileassignment.Entity.User
import com.example.mobileassignment.R
import com.example.mobileassignment.databinding.FragmentRegisterBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterFragment: Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private val userViewModel:UserViewModel by activityViewModels()



    var database = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
                //todo: add contact record to a data storage

            val confirmPass = binding.passwordConfirmText.text.toString()
            val email = binding.emailText.text.toString()
            val username = binding.usernameText.text.toString()
            val password = binding.passwordText.text.toString()
            val role = binding.roleSpinner.selectedItem.toString()
            val phone = binding.registerPhoneNumber.text.toString()
            val address = binding.address.text.toString()
            val photoURL = ""

            var errorMsg: Unit? = null

            if(email.isEmpty()){
               errorMsg = binding.textValidationEmail.setText("Required")

            }else if(!email.matches("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex())){
               errorMsg = binding.textValidationEmail.setText("Wrong email format")

            }else{
                binding.textValidationEmail.isVisible = false
            }

            if(username.isEmpty()){
                errorMsg = binding.textValidationUsername.setText("Required")
            }else{
                binding.textValidationUsername.isVisible = false
            }


            val databaseRef = FirebaseDatabase.getInstance().reference.child("user")
                .orderByChild("username").equalTo(username)


            databaseRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    if(username.equals(databaseRef)){
                        errorMsg = binding.textValidationUsername.setText("Username Exist")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

            if(phone.isEmpty()){
                errorMsg =  binding.textValidationPhoneNumber.setText("Required")
            }else{
                binding.textValidationPhoneNumber.isVisible = false
            }

            if(address.isEmpty()){
                errorMsg = binding.textValidationAddress.setText("Required")

            }else{
                binding.textValidationAddress.isVisible = false
            }

            if(password.isEmpty()){
                errorMsg = binding.textValidationPassword.setText("Required")
            }else if(password.length < 8){
                errorMsg = binding.textValidationPassword.setText("Minimum 8 Character Password")
            }
            else if(!password.matches(".*[@#\\\$%^&+=].*".toRegex())){
                errorMsg = binding.textValidationPassword.setText("Must Contains 1 Special Character")
            }else{
                binding.textValidationPassword.isVisible = false
            }

            if(confirmPass.isEmpty()){
                errorMsg = binding.textValidationConfirmPassword.setText("Required")

            }else if(confirmPass != password){
                errorMsg = binding.textValidationConfirmPassword.setText("Confirm Password must match with Password")
            }else{
                binding.textValidationConfirmPassword.isVisible = false
            }

            if(binding.roleSpinner.selectedItem.toString().trim() == "Please Select Role"){
                errorMsg = binding.textValidationRoleSpinner.setText("Required")

            }else if(binding.roleSpinner.selectedItem.toString().trim() == "Farmer" || binding.roleSpinner.selectedItem.toString().trim() == "Client"){
                binding.textValidationRoleSpinner.isVisible = false
            }

            if(errorMsg == null) {
                val newUser = User(username, password, role, email,phone,address,photoURL)

                val database: DatabaseReference = Firebase.database.getReference("user")
                database.child(newUser.username).setValue(newUser)
                Toast.makeText(context, "Profile Saved", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.nav_login)

            }

                //Return back to the First Fragment
                //val navController = activity?.findNavController(R.id.nav_host_fragment_content_main)
                //navController?.navigateUp()
                //findNavController().navigateUp()
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}