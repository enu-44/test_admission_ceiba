package com.admission.testceiba.domain.repository

import com.admission.testceiba.domain.model.PostDom

interface IPostRepository {
    suspend fun getPostsByUserId(userId:Int): List<PostDom>
}