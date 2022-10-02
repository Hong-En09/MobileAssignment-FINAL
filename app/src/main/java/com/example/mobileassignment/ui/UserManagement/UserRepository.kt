package com.example.mobileassignment.ui.UserManagement

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mobileassignment.Entity.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository(private val userDao: UserDao) {
    val allUser: LiveData<List<User>> = userDao.getAll()

    @WorkerThread
    suspend fun insert(user: User){
        userDao.insert((user))
    }
    @WorkerThread
    suspend fun delete(user: User){
        userDao.delete((user))
    }
    @WorkerThread
    suspend fun update(user: User){
        userDao.update((user))
    }

    fun syncUser(userList: List<User>){



    }


}