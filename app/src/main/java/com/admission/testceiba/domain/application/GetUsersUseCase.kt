package com.admission.testceiba.domain.application

import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.domain.repository.IUserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(): List<UserDom> {
        val usersLocal = userRepository.getLocalUsers()
        return usersLocal.ifEmpty {
            val result = userRepository.getRemoteUsers()
            userRepository.saveLocalUsers(result)
            result
        }
    }
}