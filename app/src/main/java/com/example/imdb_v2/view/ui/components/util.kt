package com.example.imdb_v2.view.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.imdb_v2.view.ui.theme.mainYellow
import androidx.compose.ui.text.font.FontWeight
import com.example.imdb_v2.view.ui.theme.darkGray
import com.example.imdb_v2.view.ui.theme.lightGray


@Composable
fun PrimaryButton( buttonText: String ,onClickCallback: ()-> Unit){
    Button(
        onClick = {onClickCallback()},
        colors = ButtonColors(
            mainYellow,
            darkGray,
            lightGray,
            lightGray
        )
    ) {
            Text(
                text = buttonText,
                fontWeight = FontWeight.ExtraBold
            )
    }
}