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


internal class SearchUsersByNameUseCaseTest{
    @RelaxedMockK
    @Mock
    private lateinit var userRepository: IUserRepository

    lateinit var searchUsersByNameUseCase:SearchUsersByNameUseCase
    private val usersFake = listOf(UserDom(id = 1, name = "Enuar", phone = "123456789", email = "enu.developer@gmail.com"))
    private val query = "Enuar"

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        searchUsersByNameUseCase = SearchUsersByNameUseCase(userRepository)
    }

    @Test
    fun `when search value is "Enuar", the database return data`() = runBlocking {
        //Given
        coEvery { userRepository.searchLocalUsersByName(query) } returns usersFake
        coEvery { userRepository.getRemoteUsers() } returns usersFake

        //When
        val response = searchUsersByNameUseCase(query)

        //Then
        coVerify(exactly = 1) { userRepository.searchLocalUsersByName(query) }
        assert(response == usersFake)
    }
}