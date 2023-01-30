package com.admission.testceiba.domain.application

import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.domain.repository.IUserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(userId:Int ): UserDom =
        userRepository.getLocalUserById(userId)
}