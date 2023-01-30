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

internal class GetUserByIdUseCaseTest {

    @RelaxedMockK
    @Mock
    private lateinit var userRepository: IUserRepository

    lateinit var getUserByIdUseCase:GetUserByIdUseCase

    private val userFake = UserDom(id = 1, name = "Enuar", phone = "123456789", email = "enu.developer@gmail.com")
    private val userId = 1

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getUserByIdUseCase = GetUserByIdUseCase(userRepository)
    }

    @Test
    fun `when get value is "1", the api return data of user`() = runBlocking {
        //Given
        coEvery { userRepository.getLocalUserById(userId) } returns userFake

        //When
        val response = getUserByIdUseCase(userId)

        //Then
        coVerify(exactly = 1) { userRepository.getLocalUserById(userId) }
        assert(userFake == response)
    }
}