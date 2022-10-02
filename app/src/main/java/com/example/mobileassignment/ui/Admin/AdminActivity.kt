package com.example.mobileassignment.ui.Admin

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mobileassignment.Entity.ProductList
import com.example.mobileassignment.Entity.ProductPhoto
import com.example.mobileassignment.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class AdminActivity : AppCompatActivity() {
    lateinit var ImageUri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        imageUploadAdmin.setOnClickListener{
            selectImage()
        }
        uploadButton.setOnClickListener {
            uploadImage()
        }
    }

    private fun selectImage():Boolean {
        val intent = Intent()
        intent.type = "image/png"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
        uploadButton.isVisible = true
        return true


    }

    private fun uploadImage(){

        if(this::ImageUri.isInitialized){
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Uploading file...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val name = productValueAdmin.text.toString()
            val uid = UUID.randomUUID().toString()


            val fileName = name
            val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
            storageReference.putFile(ImageUri)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully uploaded", Toast.LENGTH_SHORT).show()

                    if(progressDialog.isShowing) progressDialog.dismiss()

                }.addOnFailureListener{
                    Toast.makeText(this,"Failed uploaded", Toast.LENGTH_SHORT).show()

                    if(progressDialog.isShowing) progressDialog.dismiss()
                }
            val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)

            val database: DatabaseReference = Firebase.database.getReference("product")
            val product = ProductPhoto(fileName, fileName)

            database.child(uid).setValue(product)
        }else{
            Toast.makeText(this,"Please select a new photo", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK){
            ImageUri = data?.data!!
            imageUploadAdmin.setImageURI(ImageUri)

        }
    }
}