package com.admission.testceiba.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admission.testceiba.core.livedata.SingleLiveEvent
import com.admission.testceiba.domain.application.SearchUsersByNameUseCase
import com.admission.testceiba.domain.application.GetUsersUseCase
import com.admission.testceiba.domain.model.UserDom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val searchUsersByNameUseCase: SearchUsersByNameUseCase
):ViewModel() {
    var singleLiveEvent: SingleLiveEvent<ViewEvent> = SingleLiveEvent()
    sealed class ViewEvent {
        class  ResponseIsSuccess(val users: List<UserDom>): ViewEvent()
        object ResponseIsEmpty: ViewEvent()
        object ResponseIsLoading: ViewEvent()
    }
    fun getUsers() {
        viewModelScope.launch {
            singleLiveEvent.value = ViewEvent.ResponseIsLoading
            try {
                onResultEvent(getUsersUseCase())
            }catch (ex:Throwable){
                onResultEvent(emptyList())
            }
        }
    }

    fun onFilterUsers(query:String) {
        viewModelScope.launch {
            singleLiveEvent.value = ViewEvent.ResponseIsLoading
            try {
                onResultEvent(searchUsersByNameUseCase(query))
            }catch (ex:Throwable){
                onResultEvent(emptyList())
            }
        }
    }

    private fun onResultEvent(result:List<UserDom>){
        if(result.isNotEmpty()){
            singleLiveEvent.value = ViewEvent.ResponseIsSuccess(result)
        }else{
            singleLiveEvent.value = ViewEvent.ResponseIsEmpty
        }
    }
}