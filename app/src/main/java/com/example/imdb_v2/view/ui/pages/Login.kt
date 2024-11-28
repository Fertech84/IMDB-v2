package com.example.imdb_v2.view.ui.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imdb_v2.R
import com.example.imdb_v2.view.ui.theme.darkGray
import com.example.imdb_v2.view.ui.theme.lightGray
import com.example.imdb_v2.view.ui.theme.mainWhite
import com.example.imdb_v2.view.ui.theme.mainYellow
import com.example.imdb_v2.view.viewmodel.LoginStatus
import com.example.imdb_v2.view.viewmodel.LoginViewModel


@Composable
fun LoginPage(
    navigateToSignup: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
    loginViewModel: LoginViewModel = viewModel()
) {




    val loginStatusState = loginViewModel.loginStatus.observeAsState()



    if (loginStatusState.value == LoginStatus.loginSuccess) {

        navigateToHome()
    }
    else if (loginStatusState.value == LoginStatus.loginFailed) {
        Toast.makeText(LocalContext.current, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
        loginViewModel.reloadLoginState()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(mainYellow)
    ) {

        var emailTextFieldState by rememberSaveable { mutableStateOf("") }

        var passwordTextFieldState by rememberSaveable { mutableStateOf("") }

        var loginButtonEnableState by rememberSaveable { mutableStateOf(false) }

        Image(
            painter = painterResource(id = R.drawable.imdb_logo_2016_1),
            contentDescription = "IMDB Logo",
            modifier = Modifier
                .padding(top = 107.dp)
                .width(168.6.dp)
                .height(84.98.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Usuario",
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = darkGray,
            modifier = Modifier
                .width(269.dp)
                .height(21.dp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = emailTextFieldState,
            onValueChange = {
                emailTextFieldState = it
                if (validateCredentials(emailTextFieldState, passwordTextFieldState)) {
                    loginButtonEnableState = true
                } else {
                    loginButtonEnableState = false
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .background(mainYellow)
                .height(54.dp)
                .width(269.dp)

        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Constraseña",
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = darkGray,
            modifier = Modifier
                .width(269.dp)
                .height(21.dp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            visualTransformation = PasswordVisualTransformation(),
            value = passwordTextFieldState,
            onValueChange = {
                passwordTextFieldState = it
                if (validateCredentials(emailTextFieldState, passwordTextFieldState)) {
                    loginButtonEnableState = true
                } else {
                    loginButtonEnableState = false
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .background(mainYellow)
                .height(54.dp)
                .width(269.dp)

        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "¿Olvidaste la contraseña?",
            color = darkGray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.width(269.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))
        Button(
            onClick = {
                loginViewModel.login(emailTextFieldState, passwordTextFieldState)

            },
            enabled = loginButtonEnableState,
            colors = ButtonColors(
                contentColor = mainWhite,
                containerColor = darkGray,
                disabledContainerColor = lightGray,
                disabledContentColor = lightGray
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(269.dp)
                .height(54.dp)
        ) {
            Text(
                text = "Login",
                color = mainWhite,
                fontSize = 20.sp,
            )
        }

        Spacer(modifier = Modifier.height(42.dp))
        Text(
            text = "Ó podés ingresar con",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = darkGray
        )
        Spacer(modifier = Modifier.height(23.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(shape = CircleShape) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.apple_13),
                        contentDescription = "login with google",
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)

                    )
                }
            }

            Surface(shape = CircleShape) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_login_image),
                        contentDescription = "login with google",
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)
                    )
                }
            }

            Surface(shape = CircleShape) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.google_g_2015),
                        contentDescription = "login with google",
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)
                    )
                }
            }


        }

        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(
                text = "¿No tenes cuenta? ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 13.dp)
            )


            TextButton(
                onClick = navigateToSignup,
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = darkGray,
                    disabledContentColor = lightGray,
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Registrate",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            }


        }

        Spacer(modifier = Modifier.height(20.dp))
        TextButton(
            onClick = navigateToHome,
            colors = ButtonColors(
                containerColor = Color.Transparent,
                contentColor = darkGray,
                disabledContentColor = lightGray,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Continuar como invitado",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black
            )
        }

    }
}

fun validateCredentials(email: String, password: String): Boolean {
    return (email.contains("@") && password.length >= 8)
}


@Preview
@Composable
fun PreviewLoginPage() {
    LoginPage()
}




