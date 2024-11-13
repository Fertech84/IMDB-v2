package com.example.imdb_v2.view.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun PasswordFormTextField(
    passwordValue : String,
    onChangePasswordValue : (newValue : String)-> Unit,
    isShowingPassword : Boolean,
    showPassword : ()-> Unit
){


        OutlinedTextField(
            maxLines = 1,
            visualTransformation = if(isShowingPassword){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password ),
            value = passwordValue,
            trailingIcon = {
                val image = if (isShowingPassword){
                    Icons.Default.VisibilityOff
                }else{
                    Icons.Default.Visibility
                }
                IconButton(onClick =  showPassword ) {

                        Icon(image, contentDescription = "Unlock password")

                }
            },
            onValueChange = {onChangePasswordValue(it)},
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "Contraseña") },
            modifier = Modifier
                .width(300.dp)


            )

}


@Composable
fun UserNameFormField(
    usernameValue : String,
    onChangeUserNameValue : (newValue : String)->Unit
){
    OutlinedTextField(
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            showKeyboardOnFocus = true
            ),
        value = usernameValue,
        onValueChange = {
            onChangeUserNameValue(it)
        },
        label = { Text(text = "Nombre") },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(300.dp)
    )
}

@Composable
fun EmailFormField(
    emailValue : String,
    onChangeEmail : (newValue : String) -> Unit
){
    OutlinedTextField(
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        value = emailValue,
        onValueChange = {onChangeEmail(it)},
        shape = RoundedCornerShape(10.dp),
        label = { Text(text = "Correo electrónico") },
        modifier = Modifier
            .width(300.dp)
    )
}