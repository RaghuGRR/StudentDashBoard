package com.android.studentdashboard.ui.components.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    cornerRadius: Dp = 32.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .drawBehind {
                val width = size.width
                val height = size.height
                val cornerRadius = 35.dp.toPx()


                val archWidth = 200.dp.toPx()
                val archHeight = 55.dp.toPx()
                val centerX = width / 2

                val path = Path().apply {

                    moveTo(0f, cornerRadius)


                    quadraticTo(0f, 0f, cornerRadius, 0f)


                    lineTo(width - cornerRadius, 0f)
                    quadraticTo(width, 0f, width, cornerRadius)


                    lineTo(width, height - cornerRadius)
                    quadraticTo(width, height, width - cornerRadius, height)



                    lineTo(centerX + (archWidth / 2), height)



                    cubicTo(
                        centerX + (archWidth / 4), height,
                        centerX + (archWidth / 4), height - archHeight,
                        centerX, height - archHeight
                    )


                    cubicTo(
                        centerX - (archWidth / 4), height - archHeight,
                        centerX - (archWidth / 4), height,
                        centerX - (archWidth / 2), height
                    )


                    lineTo(cornerRadius, height)
                    quadraticTo(0f, height, 0f, height - cornerRadius)
                    close()
                }

                drawPath(path = path, color = Color.White)
            }
            .padding(top = cornerRadius, start = 24.dp, end = 24.dp, bottom = 12.dp)
    ) {
        content()
    }
}