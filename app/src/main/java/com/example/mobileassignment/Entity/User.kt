package com.example.mobileassignment.Entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User ( @PrimaryKey val username: String,
            val password: String,
            val role: String,
               val email: String) {

}