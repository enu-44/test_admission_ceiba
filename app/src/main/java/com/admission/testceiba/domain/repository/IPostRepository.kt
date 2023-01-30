package com.admission.testceiba.domain.repository

import com.admission.testceiba.domain.model.PostDom

interface IPostRepository {
    suspend fun getRemotePostsByUserId(userId:Int): List<PostDom>
}