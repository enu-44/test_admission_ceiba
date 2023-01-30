package com.admission.testceiba.data.datasource.remote

import com.admission.testceiba.network.api.ApiClient
import com.admission.testceiba.network.dto.PostDto
import com.admission.testceiba.network.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val api:ApiClient)  {
     suspend fun getUsers(): List<UserDto> {
        return withContext(Dispatchers.IO){
            try {
                val response: Response<List<UserDto>> = api.getAllUsers()
                response.body()?: emptyList()
            }catch (e: Exception){
                emptyList()
            }
        }
     }
}