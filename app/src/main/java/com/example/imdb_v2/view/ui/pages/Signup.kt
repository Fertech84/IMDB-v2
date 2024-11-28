package com.example.imdb_v2.view.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imdb_v2.R
import com.example.imdb_v2.view.model.UserUI
import com.example.imdb_v2.view.ui.components.EmailFormField
import com.example.imdb_v2.view.ui.components.PasswordFormTextField
import com.example.imdb_v2.view.ui.components.UserNameFormField
import com.example.imdb_v2.view.ui.theme.darkGray
import com.example.imdb_v2.view.ui.theme.lightGray
import com.example.imdb_v2.view.ui.theme.mainWhite
import com.example.imdb_v2.view.viewmodel.SignupStatus
import com.example.imdb_v2.view.viewmodel.SignupViewModel


@Composable
fun SignupScreen(
    navigateToLogin: () -> Unit = {},
    signupViewModel: SignupViewModel = viewModel()
) {
    var signupNameState by rememberSaveable { mutableStateOf("") }
    var signupEmailState by rememberSaveable { mutableStateOf("") }
    var signupPasswordState by rememberSaveable { mutableStateOf("") }
    var signupEnableButton by rememberSaveable { mutableStateOf(false) }
    var signupShowPasswordState by rememberSaveable { mutableStateOf(false) }
    val creationStatusState = signupViewModel.creationStatus.observeAsState()


    if (creationStatusState.value == SignupStatus.success){
        signupViewModel.closeSignup()
        navigateToLogin()
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(mainWhite)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navigateToLogin() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to login"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(45.dp))
            Image(
                painter = painterResource(id = R.drawable.imdb_logo_2016_1),
                contentDescription = "IMDB Icon",
                modifier = Modifier
                    .width(115.dp)
                    .height(62.03.dp)
            )

        }
        Spacer(modifier = Modifier.height(34.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(45.dp))
            Text(
                text = "Crear una cuenta",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = darkGray
            )
        }
        Spacer(modifier = Modifier.height(19.dp))

        UserNameFormField(
            usernameValue = signupNameState
        ) {
            signupNameState = it
            signupEnableButton = validateSignupStatus(signupNameState, signupEmailState, signupPasswordState)
        }


        Spacer(modifier = Modifier.height(20.dp))

        EmailFormField(emailValue = signupEmailState) {
            signupEmailState = it
            signupEnableButton = validateSignupStatus(signupNameState, signupEmailState, signupPasswordState)
        }

        Spacer(modifier = Modifier.height(20.dp))

        PasswordFormTextField(
            passwordValue = signupPasswordState,
            onChangePasswordValue = { passwordValue: String ->
               run {
                   signupPasswordState = passwordValue
                   signupEnableButton = validateSignupStatus(signupNameState, signupEmailState, signupPasswordState)
               }
            },
            isShowingPassword = signupShowPasswordState,
            showPassword = { signupShowPasswordState = !signupShowPasswordState }
        )


        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = if (creationStatusState.value == SignupStatus.failed) "Algo salió mal" else "",
            color = Color.Red
            )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(46.dp))
            Text(
                text = "La contraseña debe tener al menos 8 caracteres",
                fontSize = 12.sp,
                color = darkGray,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        Button(
            onClick = {
                signupViewModel.createNewUser(
                    UserUI(
                        uid = 0,
                        username = signupNameState,
                        email = signupEmailState,
                        password = signupPasswordState
                    )
                )
            },
            enabled = signupEnableButton,
            colors = ButtonColors(
                contentColor = mainWhite,
                containerColor = darkGray,
                disabledContainerColor = lightGray,
                disabledContentColor = lightGray
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(300.dp)
                .height(54.dp)
        ) {
            Text(
                text = "Aceptar",
                color = mainWhite,
                fontSize = 20.sp,
            )
        }


    }
}


@Preview
@Composable
fun PreviewSignupScreen() {
    SignupScreen()
}

private fun validateSignupStatus(name: String, email: String, password: String) =
    name.isNotEmpty() && email.contains("@") && password.length >= 8
