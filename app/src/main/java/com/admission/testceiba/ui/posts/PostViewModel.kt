package com.admission.testceiba.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admission.testceiba.core.livedata.SingleLiveEvent
import com.admission.testceiba.domain.application.GetPostsByUserIdUseCase
import com.admission.testceiba.domain.application.GetUserByIdUseCase
import com.admission.testceiba.domain.model.PostDom
import com.admission.testceiba.domain.model.UserDom
import com.admission.testceiba.ui.users.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsByUserIdUseCase: GetPostsByUserIdUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase

):ViewModel() {
    var singleLiveEvent: SingleLiveEvent<ViewEvent> = SingleLiveEvent()
    sealed class ViewEvent {
        class  ResponseFindUser(val user: UserDom): ViewEvent()
        class  ResponseIsSuccess(val posts: List<PostDom>): ViewEvent()
        object ResponseIsLoading: ViewEvent()
    }

    fun findUser(userId:Int) {
        viewModelScope.launch {
            val result = getUserByIdUseCase(userId)
            singleLiveEvent.value = ViewEvent.ResponseFindUser(result)
        }
    }

    fun getPosts(userId:Int) {
        viewModelScope.launch {
            singleLiveEvent.value = ViewEvent.ResponseIsLoading
            try {
                val result = getPostsByUserIdUseCase(userId)
                singleLiveEvent.value = ViewEvent.ResponseIsSuccess(result)
            }catch (ex:Throwable){
                singleLiveEvent.value = ViewEvent.ResponseIsSuccess(emptyList())
            }
        }
    }
}