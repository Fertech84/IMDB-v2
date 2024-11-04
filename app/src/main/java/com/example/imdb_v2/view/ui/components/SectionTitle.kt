package com.example.imdb_v2.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdb_v2.view.ui.theme.mainYellow
import com.example.imdb_v2.view.ui.theme.robotoFamily

@Composable
fun SectionTitle(title: String){
    Row {
        Spacer(modifier = Modifier.width(20.dp))
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .width(5.dp)
                    .height(20.dp)
                    .background(mainYellow)
            )
        }
        Spacer(modifier = Modifier.width(7.dp))
        Text(text = title,
            fontFamily = robotoFamily,
            fontSize = 20.sp
        )

    }
}