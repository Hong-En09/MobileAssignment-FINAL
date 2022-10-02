package com.example.mobileassignment.ui.Profile

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mobileassignment.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    lateinit var ImageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        buttonBack.setOnClickListener(){
            finish()
        }



        val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)

        var textUsername = sharedPreferences?.getString("username", null).toString()
        var pass = ""
        var role = ""
        var email = ""
        var phone = ""
        var address = ""
        var url = ""

        val databaseRef = FirebaseDatabase.getInstance().reference.child("user")
            .orderByChild("username").equalTo(textUsername)
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children){
                    pass = ds.child("password").getValue(String::class.java).toString()
                    email = ds.child("email").getValue(String::class.java).toString()
                    phone = ds.child("phoneNum").getValue(String::class.java).toString()
                    address = ds.child("address").getValue(String::class.java).toString()
                    url = ds.child("photoUrl").getValue(String::class.java).toString()
                }
                if(url != ""){
                    val storageRef = FirebaseStorage.getInstance().reference

                    val photoRef = storageRef.child("images/"+url)
                    val ONE_MEGABYTE = (1920 * 1080).toLong()
                    photoRef.getBytes(ONE_MEGABYTE)
                        .addOnSuccessListener { bytes ->
                            val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                            profilePic.setImageBitmap(bmp)
                        }.addOnFailureListener(OnFailureListener {

                        })
                }
                fixedProfileUsername.text = textUsername
                fixedProfileEmail.text = email
                editProfilePhoneNumber.text = phone
                editProfileAddress.text = address
                editProfilePassword.text = pass


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        showEditPassDialog()

        profilePic.setOnClickListener{
            selectImage()
        }
        upload.setOnClickListener {
            uploadImage()
        }

    }

    private fun selectImage():Boolean {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
        upload.isVisible = true
        return true


    }

    private fun uploadImage(){

        if(this::ImageUri.isInitialized){
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Uploading file...")
            progressDialog.setCancelable(false)
            progressDialog.show()


            val fileName = UUID.randomUUID().toString()
            val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
            storageReference.putFile(ImageUri)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully uploaded",Toast.LENGTH_SHORT).show()

                    if(progressDialog.isShowing) progressDialog.dismiss()

                }.addOnFailureListener{
                    Toast.makeText(this,"Failed uploaded",Toast.LENGTH_SHORT).show()

                    if(progressDialog.isShowing) progressDialog.dismiss()
                }
            val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)

            var username = sharedPreferences?.getString("username", null).toString()
            val database: DatabaseReference = Firebase.database.getReference("user")


            database.child(username).child("photoUrl").setValue(fileName)
        }else{
            Toast.makeText(this,"Please select a new photo",Toast.LENGTH_SHORT).show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            ImageUri = data?.data!!
            profilePic.setImageURI(ImageUri)

        }
    }

    private fun showEditPassDialog() {

        val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
        val username = sharedPreferences?.getString("username", null).toString()
        val password = sharedPreferences?.getString("pass", null).toString()
        var updateUser = ""
        var updatePassword = ""
        var updateRole = ""
        var updateEmail = ""
        var updatePhone = ""
        var updateAddress = ""
        var updateUrl = ""
        buttonChangeDetail.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_password_layout,null)
            val editTextPhone = dialogLayout.findViewById<EditText>(R.id.dialog_phone)
            val editTextAddress = dialogLayout.findViewById<EditText>(R.id.dialog_address)
            val editTextPassword = dialogLayout.findViewById<EditText>(R.id.dialog_password)
            val editTextConfirmPassword = dialogLayout.findViewById<EditText>(R.id.dialog_password)
            with(builder){
                setTitle("Update Profile")
                setPositiveButton("Confirm"){ _, _ ->
                    val phone = editTextPhone.text.toString()
                    val address = editTextAddress.text.toString()
                    val newPass = editTextPassword.text.toString()
                    val conPass = editTextConfirmPassword.text.toString()
                        if(phone == "" && newPass == "" && conPass == "" && address == ""){
                            Toast.makeText(context, "Nothing will be update", Toast.LENGTH_SHORT).show()
                        }else{
                            val database: DatabaseReference = Firebase.database.getReference("user")
                            if(phone != ""){
                                database.child(username).child("phoneNum").setValue(phone)
                            }
                            if(newPass != "" && conPass != ""){
                                if(newPass == conPass) {
                                    database.child(username).child("password").setValue(newPass)
                                }
                            }
                            if(address != ""){
                                database.child(username).child("address").setValue(address)
                            }

                            Toast.makeText(context, "Profile updated successfully.", Toast.LENGTH_SHORT).show()
                        }
                }
                setNegativeButton("Cancel"){dialog,which ->
                    Log.d("Main","Negative button clicked")
                }
                setView(dialogLayout)
                show()
            }


        }
    }


}