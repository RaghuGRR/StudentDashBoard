package com.android.studentdashboard.ui.components.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.studentdashboard.R

@Composable
fun LoginAvatarImages(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        SolidCircle(
            140.dp, Color(0xFFFFD4D5), content = {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.student_man),
                    tint = Color.Unspecified,
                    contentDescription = ""
                )
            },
            modifier = Modifier.align(Alignment.TopStart)
        )
        SolidCircle(115.dp, Color(0xFFDFF8FB), content = {
            Icon(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxSize(),
                painter = painterResource(R.drawable.student_avatar),
                tint = Color.Unspecified,
                contentDescription = ""
            )
        }, modifier = Modifier.align(Alignment.TopEnd))
        SolidCircle(
            105.dp, Color(0xFFCDFACF), content = {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.girl_student),
                    tint = Color.Unspecified,
                    contentDescription = ""
                )
            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(x = 20.dp)
        )
    }
}