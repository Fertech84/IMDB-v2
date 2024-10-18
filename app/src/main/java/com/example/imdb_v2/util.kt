package com.example.imdb_v2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.imdb_v2.ui.theme.mainYellow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.imdb_v2.ui.theme.darkGray
import com.example.imdb_v2.ui.theme.lightGray
import com.example.imdb_v2.ui.theme.mainWhite



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