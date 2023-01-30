package com.admission.testceiba.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admission.testceiba.domain.application.GetUsersByNameUseCase
import com.admission.testceiba.domain.application.GetUsersUseCase
import com.admission.testceiba.domain.model.UserDom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersByNameUseCase: GetUsersByNameUseCase
):ViewModel() {
    val isLoading= MutableLiveData<Boolean>()
    val users = MutableLiveData<List<UserDom>>()
    fun getUsers() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result:List<UserDom> = getUsersUseCase()
            onResult(result)
        }
    }

    fun onFilterUsers(query:String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result:List<UserDom> = getUsersByNameUseCase(query)
            onResult(result)
        }
    }

    fun onResult(result:List<UserDom>){
        users.postValue(result)
        isLoading.postValue(false)
    }
}