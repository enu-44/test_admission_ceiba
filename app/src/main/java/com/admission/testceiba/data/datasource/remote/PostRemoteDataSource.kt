package com.admission.testceiba.data.datasource.remote

import com.admission.testceiba.network.api.ApiClient
import com.admission.testceiba.network.dto.PostDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(private val api: ApiClient)  {
    suspend fun getPostsByUserId(userId:Int): List<PostDto> {
        return withContext(Dispatchers.IO){
            try {
                val response: Response<List<PostDto>> = api.getAllPostsByUserId(userId)
                response.body()?: emptyList()
            }catch (e:Exception){
                emptyList()
            }
        }
    }
}