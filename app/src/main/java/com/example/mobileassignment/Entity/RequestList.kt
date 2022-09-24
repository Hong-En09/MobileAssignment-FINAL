package com.example.mobileassignment.Entity

import androidx.room.PrimaryKey

class RequestList (val item: String,
                  @PrimaryKey val quantity: String) {
}