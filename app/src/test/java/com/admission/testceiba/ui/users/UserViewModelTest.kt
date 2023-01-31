package com.admission.testceiba.ui.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.admission.testceiba.domain.application.GetUsersUseCase
import com.admission.testceiba.domain.application.SearchUsersByNameUseCase
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
class UserViewModelTest {

    @RelaxedMockK
    private lateinit var getUsersUseCase: GetUsersUseCase

    @RelaxedMockK
    private lateinit var searchUsersByNameUseCase: SearchUsersByNameUseCase

    private lateinit var userViewModel: UserViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val query = "Enuar"
    val usersFake = listOf(UserDom(id = 1, name = "Enuar", phone = "123456789", email = "enu.developer@gmail.com"))

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        userViewModel = UserViewModel(getUsersUseCase, searchUsersByNameUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when call getUsersUseCase return data,  event asigne is ResponseIsSuccess`() = runBlocking {
        //Given
        coEvery { getUsersUseCase() } returns usersFake

        //When
        userViewModel.getUsers()

        //Then
        assert(userViewModel.singleLiveEvent.value is UserViewModel.ViewEvent.ResponseIsSuccess)
    }

    @Test
    fun `when call getUsersUseCase return empty list, event asigne is ResponseIsEmpty`() = runBlocking {
        //Given
        coEvery { getUsersUseCase() } returns emptyList()

        //When
        userViewModel.getUsers()

        //Then
        assert(userViewModel.singleLiveEvent.value is UserViewModel.ViewEvent.ResponseIsEmpty)
    }

    @Test
    fun `when call getUsersUseCase return exception, event asigne is ResponseIsEmpty`() = runBlocking {
        //Given
        coEvery { getUsersUseCase() }.throws(IllegalStateException("Error thrown unknown"))

        //When
        userViewModel.getUsers()

        //Then
        assert(userViewModel.singleLiveEvent.value is UserViewModel.ViewEvent.ResponseIsEmpty)
    }

    @Test
    fun `when call searchUsersByNameUseCase return data,  event asigne is ResponseIsSuccess`() = runBlocking {
        //Given
        coEvery { searchUsersByNameUseCase(query) } returns usersFake

        //When
        userViewModel.onFilterUsers(query)

        //Then
        assert(userViewModel.singleLiveEvent.value is UserViewModel.ViewEvent.ResponseIsSuccess)
    }

    @Test
    fun `when call searchUsersByNameUseCase return empty list, event asigne is ResponseIsEmpty`() = runBlocking {
        //Given
        coEvery { searchUsersByNameUseCase(query) } returns emptyList()

        //When
        userViewModel.onFilterUsers(query)

        //Then
        assert(userViewModel.singleLiveEvent.value is UserViewModel.ViewEvent.ResponseIsEmpty)
    }

    @Test
    fun `when call searchUsersByNameUseCase return exception, event asigne is ResponseIsEmpty`() = runBlocking {
        //Given
        coEvery { searchUsersByNameUseCase(query) }.throws(IllegalStateException("Error thrown unknown"))

        //When
        userViewModel.onFilterUsers(query)

        //Then
        assert(userViewModel.singleLiveEvent.value is UserViewModel.ViewEvent.ResponseIsEmpty)
    }
}