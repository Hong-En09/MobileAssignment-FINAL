package com.example.mobileassignment.ui.CustomerFolder

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobileassignment.Entity.AcceptedRequestList


@Dao
interface AcceptedRequestListDAO {

    @Query("SELECT * FROM AcceptedRequestList")
    fun getAll(): LiveData<List<AcceptedRequestList>>

    @Query("SELECT * FROM AcceptedRequestList WHERE username LIKE :name")
    suspend fun findByName(name: String): AcceptedRequestList

    @Insert
    suspend fun insert(acceptedRequestList: AcceptedRequestList)

    @Update
    suspend fun update(acceptedRequestList: AcceptedRequestList)

    @Delete
    suspend fun delete(acceptedRequestList: AcceptedRequestList)
}