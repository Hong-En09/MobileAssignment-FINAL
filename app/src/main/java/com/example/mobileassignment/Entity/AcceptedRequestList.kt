package com.example.mobileassignment.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class AcceptedRequestList (val name: String,
                           @PrimaryKey val product: String, val phoneNum: String, val status: String){
}