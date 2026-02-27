package com.example.goldapp.remote.timenow

import com.example.goldapp.remote.datamodel.TimeModel

interface TimeRequest {

    fun onSuccess(data : TimeModel)

    fun onNotSuccess(massage : String)

    fun onError(error : String)
}