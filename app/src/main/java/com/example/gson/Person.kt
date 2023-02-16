package com.example.gson


import com.google.gson.annotations.SerializedName

data class Person(

    @SerializedName("id")
    val id: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("roles")
    val roles: List<String>,

    @SerializedName("apiKey")
    val apiKey: String,

    @SerializedName("profile")
    val profile: PersonProfile,

    @SerializedName("username")
    val username: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String

)