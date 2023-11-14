package ru.nsu.ccfit.verba.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.domen.auth.SignInUseCase
import ru.nsu.ccfit.verba.domen.auth.SignUpUseCase
import ru.nsu.ccfit.verba.feature.common.VerbaUiEvent
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    var dataState by mutableStateOf(AuthState())
    private val _updateUiState =
        MutableStateFlow<AuthUiState>(AuthUiState.Nothing)
    val updateUiState = _updateUiState.asStateFlow()
    fun onEvent(uiEvent: VerbaUiEvent) {
        _updateUiState.value = AuthUiState.Nothing
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

    private fun signUp() {
        viewModelScope.launch {
            _updateUiState.value =
                when (val r  = signUpUseCase(dataState.username, dataState.email, dataState.password)) {
                    is Result.Success -> AuthUiState.SuccessSignUp
                    is Result.Error -> AuthUiState.Error(r.message)
                }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _updateUiState.value = when (val r = signInUseCase(dataState.username, dataState.password)) {
                is Result.Error -> AuthUiState.Error(r.message)
                is Result.Success -> AuthUiState.SuccessSignIn
            }
        }
    }

    data class AuthState(
        val isLoading: Boolean = false,
        val username: String = "",
        val email: String = "",
        val password: String = "",
    )

    sealed interface AuthUiState {
        class Error(val message: String) : AuthUiState
        data object SuccessSignIn : AuthUiState
        data object SuccessSignUp : AuthUiState
        data object Nothing : AuthUiState
    }
}



