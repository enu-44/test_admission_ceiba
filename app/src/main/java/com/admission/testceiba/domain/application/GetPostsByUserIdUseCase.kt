package com.admission.testceiba.domain.application

import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.domain.repository.IPostRepository
import javax.inject.Inject

class GetPostsByUserIdUseCase @Inject constructor(
    private val postRepository: IPostRepository
) {
    suspend operator fun invoke(userId:Int ): List<PostDom> =
        postRepository.getPostsByUserId(userId)
}