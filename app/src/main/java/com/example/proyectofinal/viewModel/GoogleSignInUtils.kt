package com.example.proyectofinal.viewModel

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.annotation.RequiresApi
import androidx.credentials.CredentialManager
import androidx.credentials.CredentialOption
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.example.proyectofinal.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GoogleSignInUtils {
    companion object{
        fun doGoogleSignIn(context: Context, scope: CoroutineScope,
                           launcher: ManagedActivityResultLauncher<Intent, ActivityResult>?,
                           login: ()-> Unit
        ){
            val credentialManager = CredentialManager.create(context)
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(getCredentialOptions(context))
                .build()

            scope.launch {
                try {
                    val result = credentialManager.getCredential(context, request)
                    when(result.credential){
                        is CustomCredential ->{
                            if(result.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
                                val googleTokenId = googleIdTokenCredential.idToken
                                val authClient = GoogleAuthProvider.getCredential(googleTokenId, null)
                                val user = Firebase.auth.signInWithCredential(authClient).await().user
                                user?.let {
                                    if(it.isAnonymous.not()){
                                        login.invoke()
                                    }
                                }
                            }
                        }
                    }
                } catch (e:  NoCredentialException){
                    launcher?.launch(getIntent())
                } catch (e: GetCredentialException){
                    e.printStackTrace()
                }
            }
        }

        fun getIntent(): Intent{
            return Intent(Settings.ACTION_ADD_ACCOUNT).apply {
                putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.example.proyectofinal"))
            }
        }

        fun getCredentialOptions(context: Context):CredentialOption {
            return GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setAutoSelectEnabled(false)
                .setServerClientId(context.getString(R.string.client_id))
                .build()
        }
    }
}