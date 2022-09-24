package com.example.mobileassignment.ui.UserManagement

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobileassignment.Entity.User

@Dao
interface UserDao {

    //TODO: Provide database CRUD functions
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE username LIKE :username")
    suspend fun findByName(username: String): User

    @Insert
    suspend fun insert(contact: User)

    @Update
    suspend fun update(contact: User)

    @Delete
    suspend fun delete(contact: User)
}
