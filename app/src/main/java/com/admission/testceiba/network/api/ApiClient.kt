package com.admission.testceiba.network.api

import com.admission.testceiba.network.dto.PostDto
import com.admission.testceiba.network.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET(value = "users")
    suspend fun getAllUsers(): Response<List<UserDto>>

    @GET(value = "posts")
    suspend fun getAllPostsByUserId(@Query("userId")  userId: Int): Response<List<PostDto>>
}