package com.example.gson

import com.google.gson.annotations.SerializedName

data class PersonLocation(

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("long")
    val long: Double

)