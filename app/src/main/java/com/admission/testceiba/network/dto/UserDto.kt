package com.admission.testceiba.network.dto
import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName(value = "id")val id:Int,
    @SerializedName(value = "name")val name:String,
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "phone")val phone: String
)
