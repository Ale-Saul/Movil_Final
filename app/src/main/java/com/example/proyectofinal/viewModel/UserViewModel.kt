package com.example.proyectofinal.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.User
import com.example.model.UserGenreCrossRef
import com.example.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel (
    private val repository: UserRepository
): ViewModel() {
    var selectedGenres = mutableStateListOf<String>()

    fun updateSelectedGenres(newSelectedGenres: List<String>) {
        selectedGenres.clear()
        selectedGenres.addAll(newSelectedGenres)
    }

    fun saveUser(user: User) {

        Log.d("holita", user.toString())
        viewModelScope.launch {
            val userId = repository.insert(user)
            Log.d("holiyta", selectedGenres.toString())
            selectedGenres.forEach {genreName ->
                val genreId = repository.getGenreIdByName(genreName)
                repository.insertUserGenreCrossRef(UserGenreCrossRef(userId.toInt(), genreId))
            }
        }

    }

    private var _state = MutableStateFlow<LoginState>(LoginState.LoggedOut)
    val state : StateFlow<LoginState> = _state

    sealed class LoginState {
        object Loading: LoginState()
        object LoggedOut: LoginState()
        data class DoLogin(val message: String): LoginState()
        data class Error(val message: String): LoginState()
    }

    fun doLogin(userName: String, password: String) {
        _state.value = LoginState.Loading
        viewModelScope.launch {
            if( repository.loginUser(userName, password)) {
                _state.value = LoginState.DoLogin("Success")
            } else {
                _state.value = LoginState.Error("Invalid credential")
            }
        }
    }
}