package com.admission.testceiba.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.admission.testceiba.domain.application.GetPostsByUserIdUseCase
import com.admission.testceiba.domain.application.GetUserByIdUseCase
import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.domain.model.UserDom
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostViewModelTest {

    @RelaxedMockK
    private lateinit var getUserByIdUseCase: GetUserByIdUseCase

    @RelaxedMockK
    private lateinit var getPostsByUserIdUseCase: GetPostsByUserIdUseCase

    private lateinit var postViewModel: PostViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val userFake = UserDom(id = 1, name = "Enuar", phone = "123456789", email = "enu.developer@gmail.com")
    private val postsFake = listOf(PostDom(title = "Posts 1", body = "posts body"))
    private val userId = 1

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        postViewModel = PostViewModel(getPostsByUserIdUseCase, getUserByIdUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when call getUsersUseCase return data,  event asigne is ResponseIsSuccess`() = runBlocking {
        //Given
        coEvery { getPostsByUserIdUseCase(userId) } returns postsFake

        //When
        postViewModel.getPosts(userId)

        //Then
        assert(postViewModel.singleLiveEvent.value is PostViewModel.ViewEvent.ResponseIsSuccess)
    }

    @Test
    fun `when call getUsersUseCase return empty list, event asigne is ResponseIsSuccess`() = runBlocking {
        //Given
        coEvery { getPostsByUserIdUseCase(userId) } returns emptyList()

        //When
        postViewModel.getPosts(userId)

        //Then
        assert(postViewModel.singleLiveEvent.value is PostViewModel.ViewEvent.ResponseIsSuccess)
    }

    @Test
    fun `when call getUsersUseCase return exception, event asigne is ResponseIsSuccess whit empty data`() = runBlocking {
        //Given
        coEvery { getPostsByUserIdUseCase(userId) }.throws(IllegalStateException("Error thrown unknown"))

        //When
        postViewModel.getPosts(userId)

        //Then
        assert(postViewModel.singleLiveEvent.value  is PostViewModel.ViewEvent.ResponseIsSuccess)
        assert((postViewModel.singleLiveEvent.value as PostViewModel.ViewEvent.ResponseIsSuccess).posts.isEmpty())
    }

    @Test
    fun `when call findUser return data,  event asigne is ResponseFindUser`() = runBlocking {
        //Given
        coEvery { getUserByIdUseCase(userId) } returns userFake

        //When
        postViewModel.findUser(userId)

        //Then
        assert(postViewModel.singleLiveEvent.value is PostViewModel.ViewEvent.ResponseFindUser)
    }
}