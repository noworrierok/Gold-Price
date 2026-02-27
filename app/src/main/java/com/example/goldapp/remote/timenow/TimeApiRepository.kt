package com.example.goldapp.remote.timenow

import com.example.goldapp.remote.MainRetrofitService
import com.example.goldapp.remote.datamodel.TimeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeApiRepository private constructor(){

    companion object{

        private var timeApiRepository : TimeApiRepository? = null

        val instance: TimeApiRepository
            get() {
                if (timeApiRepository == null) timeApiRepository = TimeApiRepository()
                return timeApiRepository!!
            }
    }

    fun getTime(
        request: TimeRequest
    ){

        MainRetrofitService.timeService.getTime(true).enqueue(

            object : Callback<TimeModel>{
                override fun onResponse(call: Call<TimeModel>, response: Response<TimeModel>) {
                    if (response.isSuccessful){
                        val data = response.body() as TimeModel
                        request.onSuccess(data)
                    }else{
                        request.onNotSuccess("notSuccess")
                    }
                }

                override fun onFailure(call: Call<TimeModel>, t: Throwable) {
                    request.onError("Error : ${t.message}")
                }
            }
        )
    }


}