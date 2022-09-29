package com.example.mobileassignment.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RequestList (@PrimaryKey val uniqueID: String,
                   val username: String,
                   val product: String,
                   val quantity: String,
                   val price: String) {
}