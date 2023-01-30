package com.admission.testceiba.network.dto

import com.google.gson.annotations.SerializedName

data class PostDto (
    @SerializedName(value = "title")val title:String,
    @SerializedName(value = "body")val body: String
)