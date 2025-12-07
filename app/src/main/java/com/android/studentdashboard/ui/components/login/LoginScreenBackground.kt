package com.android.studentdashboard.ui.components.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreenBackground(modifier: Modifier = Modifier) {
    Column(
        Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
        ) {
            LoginImages()
        }
        Box(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome to \nQuizzy!",
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }

}