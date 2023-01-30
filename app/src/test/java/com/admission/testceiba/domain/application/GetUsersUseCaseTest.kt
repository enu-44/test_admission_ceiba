package com.admission.testceiba.domain.application

import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.domain.repository.IUserRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


internal class GetUsersUseCaseTest {

    @RelaxedMockK
    @Mock
    private lateinit var userRepository: IUserRepository

    lateinit var getUsersUseCase:GetUsersUseCase

    private val usersFake = listOf(UserDom(id = 1, name = "Enuar", phone = "123456789", email = "enu.developer@gmail.com"))

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getUsersUseCase = GetUsersUseCase(userRepository)
    }

    @Test
    fun `when the database doesnt return anything then get values from api and save local database`() = runBlocking {
        //Given
        coEvery { userRepository.getLocalUsers() } returns emptyList()
        coEvery { userRepository.getRemoteUsers() } returns usersFake

        //When
        getUsersUseCase()

        //Then
        coVerify(exactly = 1) { userRepository.getRemoteUsers() }
        coVerify(exactly = 1) { userRepository.saveLocalUsers(usersFake) }
    }


    @Test
    fun `when the database return anything then get values from database`() = runBlocking {
        //Given
        coEvery { userRepository.getLocalUsers() } returns usersFake

        //When
        val response = getUsersUseCase()

        //Then
        coVerify(exactly = 0) { userRepository.getRemoteUsers() }
        coVerify(exactly = 0) { userRepository.saveLocalUsers(usersFake) }
        assert(response == usersFake)
    }
}