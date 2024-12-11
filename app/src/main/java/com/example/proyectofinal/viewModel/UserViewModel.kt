package com.example.proyectofinal.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.User
import com.example.model.UserGenreCrossRef
import com.example.proyectofinal.R
import com.example.repository.UserRepository
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
//import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.security.PublicKey
import java.util.UUID

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
        viewModelScope.launch(Dispatchers.IO){
            val userId = repository.insert(user)
            Log.d("holitaaa", userId.toString())
            Log.d("holiyta", selectedGenres.toString())
            selectedGenres.forEach { genreName ->
                val genreId = repository.getGenreIdByName(genreName)
                Log.d("holitaa", genreId.toString())
                withContext(Dispatchers.Main) {
                    repository.insertUserGenreCrossRef(UserGenreCrossRef(userId.toInt(), genreId))
                }
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
    private val auth = Firebase.auth

    fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    fun signInWithGoogle(context: Context) : Flow<LoginState> = callbackFlow {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(context.getString(R.string.client_id))
            .setAutoSelectEnabled(true)
            .setNonce(createNonce())
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try{
            val credentialManager = CredentialManager.create(context)
            val result= credentialManager.getCredential(context = context, request = request)
            val credential = result.credential
            when (credential) {
                is PublicKeyCredential -> {
                    val responseJson = credential.authenticationResponseJson
                }
                is PasswordCredential -> {
                    val username = credential.id
                    val password = credential.password
                }
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                        try {
                            val googleIdTokenCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)
                            val firebaseCredential = GoogleAuthProvider
                                .getCredential(
                                    googleIdTokenCredential.idToken,
                                    null
                                )
                            auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener {
                                    if (it.isSuccessful){
                                        trySend(LoginState.DoLogin("ok"))
                                    }else{
                                        trySend(LoginState.Error(message = it.exception?.message ?: ""))
                                    }
                                    close()
                                }
                        }catch (e: GoogleIdTokenParsingException){
                            trySend(LoginState.Error(message = e.message ?: ""))
                            close()
                        }

                        awaitClose()
                    }
                }
            }
        }catch (e: Exception){
            Log.d("holitaerror", e.toString())
            trySend(LoginState.Error(message = e.message ?: ""))
        }
    }

    fun doLogin(userName: String, password: String) {
        _state.value = LoginState.Loading
        viewModelScope.launch {
            if( repository.loginUser(userName, password)) {
                _state.value = LoginState.DoLogin("Success")
                val userIdCurrent = repository.getIdByUsername(userName)
                repository.saveLoginState(userIdCurrent, true)
                Log.d("holitawww", userIdCurrent.toString())
            } else {
                _state.value = LoginState.Error("Invalid credential")
            }
        }
    }
}