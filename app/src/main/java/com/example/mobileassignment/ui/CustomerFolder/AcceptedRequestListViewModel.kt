package com.example.mobileassignment.ui.CustomerFolder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mobileassignment.Entity.AcceptedRequestList
import kotlinx.coroutines.launch

class AcceptedRequestListViewModel (application: Application)
    : AndroidViewModel(application){

    var acceptedRequestList: LiveData<List<AcceptedRequestList>>
    private val acceptedRequestListRepository: AcceptedRequestListRepository

    init {
        // Create an instance of DAO
        val acceptedDAO = AcceptedRequestListDatabase.getDatabase(application).AcceptedRequestListDAO()

        //Link DAO to Repository
        acceptedRequestListRepository = AcceptedRequestListRepository(acceptedDAO)

        //Retrieve data from repository
        acceptedRequestList = acceptedRequestListRepository.allAcceptedRequestList

    }

    fun insert(acceptedRequestList: AcceptedRequestList) = viewModelScope.launch {
        acceptedRequestListRepository.insert(acceptedRequestList)
    }

    fun delete(acceptedRequestList: AcceptedRequestList) = viewModelScope.launch {
        acceptedRequestListRepository.delete(acceptedRequestList)
    }

    fun update(acceptedRequestList: AcceptedRequestList) = viewModelScope.launch {
        acceptedRequestListRepository.update(acceptedRequestList)
    }
}