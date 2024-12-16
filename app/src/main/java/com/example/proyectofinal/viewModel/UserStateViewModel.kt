package com.example.proyectofinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.User
import com.example.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserStateViewModel (
    private val repository: UserRepository
): ViewModel() {
    private var _state = MutableLiveData<User>()
    val state : LiveData<User>
        get() = _state

    fun getInfoUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.getInfoUser()
            withContext(Dispatchers.Main) {
                _state.value = user
            }
        }
    }
}