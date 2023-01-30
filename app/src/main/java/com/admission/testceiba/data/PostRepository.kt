package com.admission.testceiba.data

import com.admission.testceiba.data.datasource.remote.PostRemoteDataSource
import com.admission.testceiba.data.mappers.toDom
import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.domain.repository.IPostRepository
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postRemoteDataSource:PostRemoteDataSource
) :IPostRepository {

    override suspend fun getPostsByUserId(userId:Int): List<PostDom> =
        postRemoteDataSource
            .getPostsByUserId(userId)
            .map { it.toDom() }
}