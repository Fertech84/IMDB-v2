package com.example.imdb_v2.view.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdb_v2.R
import com.example.imdb_v2.view.ui.theme.darkGray
import com.example.imdb_v2.view.ui.theme.lightGray
import com.example.imdb_v2.view.ui.theme.mainWhite


@Composable
fun SignupScreen(
    navigateToLogin : ()-> Unit = {}
) {
    var signupNameState by rememberSaveable { mutableStateOf("") }
    var signupEmailState by rememberSaveable { mutableStateOf("") }
    var signupPasswordState by rememberSaveable { mutableStateOf("") }
    var signupEnableButton by rememberSaveable { mutableStateOf(false) }
    var signupShowPasswordState by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(mainWhite)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { /*TODO*/ }) {
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
        OutlinedTextField(
            value = signupNameState,
            onValueChange = {
                signupNameState = it
                signupEnableButton =
                    validateSignupStatus(signupNameState, signupEmailState, signupPasswordState)
            },
            label = { Text(text = "Nombre") },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = signupEmailState,
            onValueChange = {
                signupEmailState = it
                signupEnableButton =
                    validateSignupStatus(signupNameState, signupEmailState, signupPasswordState)
            },
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "Correo electrónico") },
            modifier = Modifier
                .width(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        if(!signupShowPasswordState){
            OutlinedTextField(
                visualTransformation = PasswordVisualTransformation(),
                value = signupPasswordState,
                trailingIcon = {
                    IconButton(onClick = { signupShowPasswordState = !signupShowPasswordState }) {
                        if (!signupShowPasswordState) {
                            Icon(Icons.Filled.Lock, contentDescription = "Unlock password")
                        } else {
                            Icon(Icons.Outlined.Lock, contentDescription = "Lock")
                        }
                    }
                },
                onValueChange = {
                    signupPasswordState = it
                    signupEnableButton =
                        validateSignupStatus(signupNameState, signupEmailState, signupPasswordState)
                },
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "Contraseña") },
                modifier = Modifier
                    .width(300.dp)
            )
        }else {
            OutlinedTextField(
                value = signupPasswordState,
                trailingIcon = {
                    IconButton(onClick = { signupShowPasswordState = !signupShowPasswordState }) {
                        if (!signupShowPasswordState) {
                            Icon(Icons.Filled.Lock, contentDescription = "Unlock password")
                        } else {
                            Icon(Icons.Outlined.Lock, contentDescription = "Lock")
                        }
                    }
                },
                onValueChange = {
                    signupPasswordState = it
                    signupEnableButton =
                        validateSignupStatus(signupNameState, signupEmailState, signupPasswordState)
                },
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "Contraseña") },
                modifier = Modifier
                    .width(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
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
            onClick = navigateToLogin,
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
