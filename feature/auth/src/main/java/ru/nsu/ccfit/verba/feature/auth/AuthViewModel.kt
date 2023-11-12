package ru.nsu.ccfit.verba.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verba.core.common.VerbaAppEvent
import ru.nsu.ccfit.verba.core.common.VerbaUiEvent
import ru.nsu.ccfit.verba.domen.SignInResult
import ru.nsu.ccfit.verba.domen.SignInUseCase
import ru.nsu.ccfit.verba.domen.SignUpResult
import ru.nsu.ccfit.verba.domen.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel(){

    var state by mutableStateOf(AuthState())
    fun onEvent(uiEvent: VerbaUiEvent) {
        when (uiEvent) {
            is AuthUiEvent.UsernameChanged -> {
                state = state.copy(username = uiEvent.value)
            }

            is AuthUiEvent.PasswordChanged -> {
                state = state.copy(password = uiEvent.value)
            }

            is AuthUiEvent.EmailChanged -> {
                state = state.copy(email = uiEvent.value)
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
            when (signUpUseCase(state.username, state.email, state.password)) {
                is SignUpResult.Success -> {
                    TODO("К следующему экрану")
                }

                is SignUpResult.NoCorrectData -> TODO()
                is SignUpResult.UnknownError -> TODO()
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            when (signInUseCase(state.username, state.password)) {
                is SignInResult.Authorized -> {
                    TODO("К следующему экрану")
                }

                is SignInResult.Unauthorized -> {
                   // eventChannel.emit(AuthEvent.Failure)
                }

                is SignInResult.UnknownError -> TODO("Ошибка")
            }
        }
    }

    data class AuthState(
        val isLoading: Boolean = false,
        val username: String = "",
        val email: String = "",
        val password: String = "",
    )

    sealed class AuthEvent : VerbaAppEvent {
        data object Success : AuthEvent()
        data object Failure : AuthEvent()
    }
}



