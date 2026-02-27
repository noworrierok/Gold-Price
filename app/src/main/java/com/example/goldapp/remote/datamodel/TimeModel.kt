package com.example.goldapp.remote.datamodel

import com.google.gson.annotations.SerializedName

data class TimeModel(
    val success: Int,
    val message: String,
    val help: String,
    val date: Date
)

data class Date(
    @SerializedName("F") val fValue: String,
    @SerializedName("Y") val yValue: String,
    @SerializedName("j") val jValue: String,
    @SerializedName("l") val lValue: String
)