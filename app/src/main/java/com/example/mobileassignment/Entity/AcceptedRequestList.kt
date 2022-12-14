package com.example.mobileassignment.Entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class AcceptedRequestList (@PrimaryKey val uniqueID: String,
                           val username: String,
                           val product: String,
                           val quantity: String,
                           val price: String,
                           val status: String,
                           val photoURL: String,
                           val dealer: String,
                           val address:String,
val phone:String) {
    @Ignore
    constructor():this("","","","","","", "", "","", "")
}