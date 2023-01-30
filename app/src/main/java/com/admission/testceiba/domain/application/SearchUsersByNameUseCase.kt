package com.admission.testceiba.domain.application

import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.domain.repository.IUserRepository
import javax.inject.Inject

class SearchUsersByNameUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(query:String ): List<UserDom> =
        userRepository.searchLocalUsersByName(query)
}