package com.example.mobileassignment.Entity

import androidx.room.PrimaryKey

class CustomerOrderList (val name: String,
                        @PrimaryKey val address: String, val phoneNum: String, val status: String) {
}