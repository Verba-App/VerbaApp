package ru.nsu.ccfit.verba.feature.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nsu.ccfit.verba.core.designsystem.screamingGreen
import ru.nsu.ccfit.verba.core.ui.VerbaErrorToast
import ru.nsu.ccfit.verba.core.ui.VerbaSuccessToast
import ru.nsu.ccfit.verba.feature.R

@Composable
fun AuthScreen(viewModel: AuthViewModel = hiltViewModel(), successAuth: () -> Unit) {
    val context = LocalContext.current

    val uiState by viewModel.updateUiState.collectAsState()

    when (val state = uiState) {
        is AuthViewModel.AuthUiState.Error -> {VerbaErrorToast(context, state.message)}
        is AuthViewModel.AuthUiState.SuccessSignIn -> successAuth.invoke()
        is AuthViewModel.AuthUiState.SuccessSignUp -> VerbaSuccessToast(context, stringResource(R.string.sign_in))
        is AuthViewModel.AuthUiState.Nothing -> Unit
    }

    AuthBlock(dataState = viewModel.dataState, onEvent = { viewModel.onEvent(it) })

}

@Composable
fun AuthBlock(dataState: AuthViewModel.AuthState, onEvent: (AuthUiEvent) -> Unit) {

    var sign by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 40.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            if (sign) {

                MyTextField("Username", dataState.username) {
                    onEvent(AuthUiEvent.UsernameChanged(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                MyTextField("Password", dataState.password) {
                    onEvent(AuthUiEvent.PasswordChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyButton(name = "SignIn") {
                    onEvent(AuthUiEvent.SignIn)
                }


            } else {


                MyTextField("Username", dataState.username) {
                    onEvent(AuthUiEvent.UsernameChanged(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                MyTextField("Email", dataState.email) {
                    onEvent(AuthUiEvent.EmailChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyTextField("Password", dataState.password) {
                    onEvent(AuthUiEvent.PasswordChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyButton(name = "SignUp") {
                    onEvent(AuthUiEvent.SignUp)
                }


            }

            if (sign) {
                Text(
                    text = stringResource(R.string.sign_up),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 40.dp)
                        .clickable {
                            sign = !sign
                        }
                )
            } else {
                Text(
                    text = stringResource(R.string.sign_in),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 40.dp)
                        .clickable {
                            sign = !sign
                        }
                )
            }

        }

    }
}

@Composable
fun MyTextField(name: String, value: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onChange.invoke(it)
        },
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = Color.White,
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
        ),
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = name, style = MaterialTheme.typography.button
            )
        }
    )
}

@Composable
fun MyButton(name: String, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color.Green),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = screamingGreen,
            contentColor = Color.White
        )
    ) {
        Text(text = name, style = MaterialTheme.typography.button)
    }
}

sealed class AuthUiEvent : ru.nsu.ccfit.verba.feature.common.VerbaUiEvent {
    data class UsernameChanged(val value: String) : AuthUiEvent()
    data class EmailChanged(val value: String) : AuthUiEvent()
    data class PasswordChanged(val value: String) : AuthUiEvent()
    data object SignUp : AuthUiEvent()
    data object SignIn : AuthUiEvent()
}