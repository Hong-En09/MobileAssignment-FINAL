package com.example.mobileassignment.ui.UserManagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mobileassignment.Entity.User
import com.example.mobileassignment.databinding.FragmentRegisterBinding
import com.google.firebase.database.FirebaseDatabase




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
                val email = binding.emailText.text.toString()
                val username = binding.usernameText.text.toString()
                val password = binding.passwordText.text.toString()
                val role = binding.roleSpinner.selectedItem.toString()
                val newUser = User(username, password, role, email)

                userViewModel.insert(newUser)
            userViewModel.userList.observe(viewLifecycleOwner){
                userViewModel.syncUser()
                Toast.makeText(context, "Profile Saved", Toast.LENGTH_SHORT).show()

            }



                //Return back to the First Fragment
                //val navController = activity?.findNavController(R.id.nav_host_fragment_content_main)
                //navController?.navigateUp()
                //findNavController().navigateUp()


        }
        binding.button.setOnClickListener{
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}