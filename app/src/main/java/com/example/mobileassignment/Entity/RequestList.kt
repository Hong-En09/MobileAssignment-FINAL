package com.example.mobileassignment.Entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class RequestList(@PrimaryKey val uniqueID: String,
                  val username: String,
                  val product: String,
                  val quantity: String,
                  val price: String
) {
    @Ignore
    constructor():this("","","","","")
}