package ru.nsu.ccfit.verba.feature.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nsu.ccfit.verba.core.common.VerbaUiEvent
import ru.nsu.ccfit.verba.core.designsystem.screamingGreen
import ru.nsu.ccfit.verba.feature.R

@Composable
fun AuthScreen(viewModel: AuthViewModel = hiltViewModel(), successAuth: () -> Unit) {
        val state = viewModel.state
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

            var sign by remember {
                mutableStateOf(true)
            }


            if (sign) {

                MyTextField("Username", state.username) {
                    viewModel.onEvent(AuthUiEvent.UsernameChanged(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                MyTextField("Password", state.password) {
                    viewModel.onEvent(AuthUiEvent.PasswordChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyButton(name = "SignIn") {
                    viewModel.onEvent(AuthUiEvent.SignIn)
                }


            } else {


                MyTextField("Username", state.username) {
                    viewModel.onEvent(AuthUiEvent.UsernameChanged(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                MyTextField("Email", state.email) {
                    viewModel.onEvent(AuthUiEvent.EmailChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyTextField("Password", state.password) {
                    viewModel.onEvent(AuthUiEvent.PasswordChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyButton(name = "SignUp") {
                    viewModel.onEvent(AuthUiEvent.SignUp)
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

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
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

sealed class AuthUiEvent : VerbaUiEvent {
    data class UsernameChanged(val value: String) : AuthUiEvent()
    data class EmailChanged(val value: String) : AuthUiEvent()
    data class PasswordChanged(val value: String) : AuthUiEvent()
    data object SignUp : AuthUiEvent()
    data object SignIn : AuthUiEvent()
}