package com.android.studentdashboard.ui.components.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.studentdashboard.R


@Composable
fun LoginImages(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        SolidCircle(
            40.dp,
            Color(0xFFCDFACF),
            Modifier
                .align(Alignment.TopEnd)
                .offset(x = 15.dp)
        )
        Icon(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp, start = 25.dp),
            painter = painterResource(R.drawable.ic_pw_logo),
            tint = Color.Unspecified,
            contentDescription = ""
        )

        LoginAvatarImages(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .padding(top = 50.dp)
                .fillMaxHeight(0.7f)
                .align(Alignment.Center)
        )
        SolidCircle(
            40.dp,
            Color(0xFFCBA9AA),
            Modifier
                .align(Alignment.TopStart)
                .offset(x = (-20).dp, y = 30.dp)
        )
        SolidCircle(
            12.dp,
            Color(0xFFDFF8FB),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 20.dp, y = 70.dp)
        )
        SolidCircle(
            12.dp,
            Color(0xFFFFDF92),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-20).dp, y = 110.dp)
        )
        Icon(
            modifier = Modifier.align(Alignment.BottomStart),
            painter = painterResource(R.drawable.ic_pi),
            tint = Color.Unspecified,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun LoginImagesPreview() {
    LoginImages()
}




