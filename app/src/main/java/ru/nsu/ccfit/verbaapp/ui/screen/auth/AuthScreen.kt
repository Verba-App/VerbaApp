package ru.nsu.ccfit.verbaapp.ui.screen.auth

import kotlinx.coroutines.flow.collect
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.nsu.ccfit.verbaapp.R
import ru.nsu.ccfit.verbaapp.core.auth.domen.AuthResult
import ru.nsu.ccfit.verbaapp.ui.screen.destinations.MainScreenDestination

import ru.nsu.ccfit.verbaapp.ui.theme.screamingGreen

@Composable
@Destination(start = true)
fun AuthScreen(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navigator.navigate(MainScreenDestination) {
                        popUpTo(MainScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You're not authorized",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
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

            var sign by remember {
                mutableStateOf(true)
            }


            if (sign) {

                MyTextField("Username", state.signInUsername) {
                    viewModel.onEvent(AuthUiEvent.SignInUsernameChanged(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                MyTextField("Password", state.signInPassword) {
                    viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyButton(name = "SignIn") {
                    viewModel.onEvent(AuthUiEvent.SignIn)
                }


            } else {


                MyTextField("Username", state.signUpUsername) {
                    viewModel.onEvent(AuthUiEvent.SignUpUsernameChanged(it))
                }

                Spacer(modifier = Modifier.height(16.dp))

                MyTextField("Email", state.signUpEmail) {
                    viewModel.onEvent(AuthUiEvent.SignUpEmailChanged(it))
                }

                Spacer(modifier = Modifier.height(30.dp))

                MyTextField("Password", state.signUpPassword) {
                    viewModel.onEvent(AuthUiEvent.SignUpPasswordChanged(it))
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