package ru.nsu.ccfit.verba.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verba.core.model.onError
import ru.nsu.ccfit.verba.core.model.onSuccess
import ru.nsu.ccfit.verba.domen.auth.AuthorizationUseCase
import ru.nsu.ccfit.verba.domen.auth.SignInUseCase
import ru.nsu.ccfit.verba.domen.auth.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val authorizationUseCase: AuthorizationUseCase,
) : ViewModel() {

    var dataState by mutableStateOf(AuthState())

    private val stateUiChannel = Channel<AuthUiState>()
    val stateUi = stateUiChannel.receiveAsFlow()

    init {
        authenticate()
    }

    fun onEvent(uiEvent: AuthUiEvent) {

        when (uiEvent) {
            is AuthUiEvent.UsernameChanged -> {
                dataState = dataState.copy(username = uiEvent.value)
            }

            is AuthUiEvent.PasswordChanged -> {
                dataState = dataState.copy(password = uiEvent.value)
            }

            is AuthUiEvent.EmailChanged -> {
                dataState = dataState.copy(email = uiEvent.value)
            }

            is AuthUiEvent.SignIn -> {
                signIn()
            }

            is AuthUiEvent.SignUp -> {
                signUp()
            }
        }

    }

    private fun signUp() =
        viewModelScope.launch {
            signUpUseCase(dataState.username, dataState.email, dataState.password)
                .onSuccess { stateUiChannel.send(AuthUiState.SuccessSignUp) }
                .onError { stateUiChannel.send(AuthUiState.Error(it)) }
        }


    private fun signIn() =
        viewModelScope.launch {
            signInUseCase(dataState.username, dataState.password)
                .onSuccess { stateUiChannel.send(AuthUiState.SuccessSignIn) }
                .onError { stateUiChannel.send(AuthUiState.Error(it)) }
        }


    private fun authenticate() {
        viewModelScope.launch {
            authorizationUseCase().onSuccess {
                stateUiChannel.send(AuthUiState.SuccessSignIn)
            }
                .onError { stateUiChannel.send(AuthUiState.Error(it)) }
        }
    }

    data class AuthState(
        val isLoading: Boolean = false,
        val username: String = "rodkot",
        val email: String = "",
        val password: String = "rodkot",
    )

    sealed interface AuthUiState {
        class Error(val message: String) : AuthUiState
        data object SuccessSignIn : AuthUiState
        data object SuccessSignUp : AuthUiState
        data object Nothing : AuthUiState
    }
}



