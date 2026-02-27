package com.example.goldapp.remote.timenow

import com.example.goldapp.remote.datamodel.TimeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApiService {

    @GET("data/now")
    fun getTime(
        @Query("short") short : Boolean // ارسال پارامتر به سمت سرور
    ): Call<TimeModel>
}