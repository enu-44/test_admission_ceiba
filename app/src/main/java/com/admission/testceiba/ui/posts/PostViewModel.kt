package com.admission.testceiba.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admission.testceiba.domain.application.GetPostsByUserIdUseCase
import com.admission.testceiba.domain.application.GetUserByIdUseCase
import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.domain.model.UserDom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsByUserIdUseCase: GetPostsByUserIdUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase

):ViewModel() {

    val selectedUser = MutableLiveData<UserDom>()
    val posts = MutableLiveData<List<PostDom>>()
    val isLoading= MutableLiveData<Boolean>()

    fun findUser(userId:Int) {
        viewModelScope.launch {
            val result = getUserByIdUseCase(userId)
            selectedUser.postValue(result)
        }
    }

    fun getPosts(userId:Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPostsByUserIdUseCase(userId)
            posts.postValue(result)
            isLoading.postValue(false)

        }
    }
}