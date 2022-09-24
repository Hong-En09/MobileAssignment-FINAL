package com.example.mobileassignment.ui.UserManagement

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mobileassignment.Entity.User
import kotlinx.coroutines.launch


class UserViewModel (application: Application)
    : AndroidViewModel(application) {
    var userList: LiveData<List<User>>
    private val userRepository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()

        userRepository = UserRepository(userDao)

        userList = userRepository.allUser
    }

    fun insert(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        userRepository.delete(user)
    }

    fun update(user: User) = viewModelScope.launch {
        userRepository.update(user)
    }

    fun syncUser(){
        Log.i("FK", "You")
        userRepository.syncUser(userList.value!!.toList())
    }

}