package com.example.mobileassignment.ui.CustomerFolder

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mobileassignment.Entity.AcceptedRequestList

class AcceptedRequestListRepository(private var acceptedRequestListDAO: AcceptedRequestListDAO){

    val allAcceptedRequestList: LiveData<List<AcceptedRequestList>> = acceptedRequestListDAO.getAll()

    @WorkerThread
    suspend fun  insert(acceptedRequestList: AcceptedRequestList){
        acceptedRequestListDAO.insert(acceptedRequestList)
    }

    @WorkerThread
    suspend fun  delete(acceptedRequestList: AcceptedRequestList){
        acceptedRequestListDAO.delete(acceptedRequestList)
    }

    @WorkerThread
    suspend fun  update(acceptedRequestList: AcceptedRequestList){
        acceptedRequestListDAO.update(acceptedRequestList)
    }


}