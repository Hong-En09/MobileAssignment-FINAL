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
        val database: DatabaseReference = Firebase.database.getReference("user")

        for(user in userList.listIterator()){
            database.child(user.username).setValue(user)
            /*database.child("user").child(id)
                .child(user.email).child("username")
                .setValue(user.username)


            database.child("user").child(id)
                .child(user.email).child("password")
                .setValue(user.password)

            database.child("user").child(id)
                .child(user.email).child("role")
                .setValue(user.role)

            database.child("user").child(id)
                .child(user.email).child("email")
                .setValue(user.email)

            database.child("user").child("username").child(user.username).child("username").setValue(user.username)
            database.child("user").child("username").child(user.username).child("email").setValue(user.email)
            database.child("user").child("username").child(user.username).child("role").setValue(user.role)
            database.child("user").child("username").child(user.username).child("password").setValue(user.password)*/
        }
    }


}