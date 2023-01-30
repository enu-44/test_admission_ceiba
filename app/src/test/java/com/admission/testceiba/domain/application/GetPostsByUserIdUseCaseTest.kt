package com.admission.testceiba.domain.application

import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.domain.repository.IPostRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


internal class GetPostsByUserIdUseCaseTest{
    @RelaxedMockK
    @Mock
    private lateinit var postRepository: IPostRepository

    lateinit var getPostsByUserIdUseCase:GetPostsByUserIdUseCase
    private val postsFake = listOf(PostDom(title = "Posts 1", body = "posts body"))
    private val userId = 1

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getPostsByUserIdUseCase = GetPostsByUserIdUseCase(postRepository)
    }

    @Test
    fun `when get value is "1", the api return data posts`() = runBlocking {
        //Given
        coEvery { postRepository.getRemotePostsByUserId(userId) } returns postsFake

        //When
        val response = getPostsByUserIdUseCase(userId)

        //Then
        coVerify(exactly = 1) { postRepository.getRemotePostsByUserId(userId) }
        assert(response == postsFake)
    }
}