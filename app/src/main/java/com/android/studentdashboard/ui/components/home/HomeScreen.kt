package com.android.studentdashboard.ui.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.domain.model.DailyInsightModel
import com.android.domain.model.DashboardModel
import com.android.domain.model.DayStreakModel
import com.android.domain.model.StudentProfileModel
import com.android.domain.model.WeeklyPerformanceModel
import com.android.studentdashboard.ui.util.UiState
import com.android.studentdashboard.ui.viewmodels.HomeViewModel
import com.android.studentdashboard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier,onLogout: () -> Unit,homeViewModel: HomeViewModel = hiltViewModel()) {

    val uiState by homeViewModel.homeState.collectAsStateWithLifecycle()

    Scaffold() { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7F9)), // Light Gray Background
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(color = Color.Black)
                }
                is UiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = state.message, color = Color.Red, modifier = Modifier.padding(16.dp))
                        Button(onClick = { homeViewModel.loadData() }) {
                            Text("Retry")
                        }
                    }
                }
                is UiState.Success -> {
                    DashboardContent(data = state.data,onLogout)
                }
                is UiState.Idle -> {}
            }
        }
    }
}



@Composable
fun DashboardContent(data: DashboardModel,logOut: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. Header Section
        data.studentProfile?.let{
            item {
                Row(modifier = Modifier.fillMaxWidth()){
                    StudentHeader(profile = it)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.ic_log_out),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(30.dp).clickable{
                            logOut()
                        }
                    )
                }
            }
        }

        // 2. Stats Grid
        item {
            StatsRow(data = data)
        }

        data.dailyInsight?.let {
            item {
                DailySummaryCard(
                    it
                )
            }
        }
        data.weeklyPerformance?.let {
            item {
                WeeklySummaryCard(
                    it
                )
            }
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}



@Composable
fun StudentHeader(profile: StudentProfileModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "Hello, ${profile.name}!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(text = profile.standard, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun StatsRow(data: DashboardModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        data.studentProfile?.let {
            StatCard(
                label = "Availability",
                value = it.availabilityStatus,
                color = Color(0xFF81D792).copy(alpha = 0.1f), // Green"
                borderColor = Color(0xFF22C55D),
                iconId = R.drawable.ic_availability,
                modifier = Modifier.weight(1f)
            )

            StatCard(
                label = "Attempts",
                value = it.quizAttempts.toString(),
                color = Color(0xFFF4B16F).copy(alpha = 0.1f), // Blue
                borderColor = Color(0xFFF4B16F) ,
                iconId = R.drawable.ic_attempts,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "Accuracy",
                value = it.accuracyPercentage,
                color = Color(0xFFFF9191).copy(alpha = 0.1f), // Green
                borderColor = Color(0xFFFF4F4F),
                iconId = R.drawable.ic_accuracy,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatCard(label: String, value: String, color: Color, borderColor: Color,iconId:Int,modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color),
        border = BorderStroke(1.dp,borderColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                painter = painterResource(iconId),
                tint = Color.Unspecified,
                contentDescription = "",
            )
            Text(text = label, fontSize = 12.sp, color = Color.DarkGray)
            Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold,color = borderColor)

        }
    }
}

@Composable
fun DailySummaryCard(
    dailySummary: DailyInsightModel
) {
    val context = LocalContext.current
    val cleanName = dailySummary.characterImageUrl?.substringBeforeLast(".")
    val drawableId = remember(cleanName) {
        context.resources.getIdentifier(
            cleanName,
            "drawable",
            context.packageName
        )
    }

    Column {
        Text(
            text = "Today’s Summary",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF996EB5).copy(alpha = 0.1f)),
            border = BorderStroke(1.dp,Color(0xFF996EB5)),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                dailySummary.characterImageUrl?.let {
                    Image(
                        modifier = Modifier.size(60.dp),
                        painter = painterResource(if(drawableId!=0)drawableId else R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                    )
                }
                Text(dailySummary.mood, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF996EB5))
                Text(text = "\"${dailySummary.description}\"", fontWeight = FontWeight.Thin, fontSize = 14.sp, color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                if (dailySummary.videoRecommendation.title.isNotBlank()) {
                    Surface(
                        color = Color(0xFF212121),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_play),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Watch: ${dailySummary.videoRecommendation.title}",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopicItem(name: String, isTrendingUp: Boolean) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Status Dot
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (isTrendingUp) Color.Green else Color.Red)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(text = name, modifier = Modifier.weight(1f), fontSize = 14.sp)

            // Icon
            Icon(
                imageVector = if (isTrendingUp) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = if (isTrendingUp) Color.Green else Color.Red
            )
        }
    }
}

@Composable
fun EmptyStateCard(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(text = message, color = Color.Gray, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun WeeklySummaryCard(data: WeeklyPerformanceModel, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Weekly Overview",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp,Color.DarkGray),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(0.dp)
        ){
            Column(Modifier.padding(12.dp)) {
                Text(
                    text = "Quiz Streak", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))

                WeeklyStreakRow(streak = data.streak)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Accuracy", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = data.scoreLabel,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(modifier = Modifier.fillMaxWidth(),progress = { data.overallScore.toFloat() / 100 }, color = Color(0xFFFF4F4F), trackColor = Color(0xFFFF4F4F).copy(alpha = 0.1f), strokeCap = StrokeCap.Round, gapSize = 0.dp, drawStopIndicator = {})

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Performance by Topic", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                val topics = data.topicTrends
                if (topics.isNotEmpty()) {
                    topics.forEach { topic ->
                        TopicItem(name = topic.topicName, isTrendingUp = topic.isTrendingUp)
                    }
                } else {
                    EmptyStateCard(message = " Great work! No weak topics found this week.")
                }
            }


        }

    }

}


@Composable
fun WeeklyStreakRow(
    streak: List<DayStreakModel>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // Distributes circles evenly
        verticalAlignment = Alignment.CenterVertically
    ) {
        streak.forEach { day ->
            StreakBubble(
                dayLabel = day.dayLabel,
                isCompleted = day.isCompleted
            )
        }
    }
}

@Composable
fun StreakBubble(
    dayLabel: String,
    isCompleted: Boolean,
    size: Dp = 30.dp
) {
    // Colors
    val successColor = Color(0xFF22C55E) // Vibrant Green
    val pendingColor = Color(0xFF9CA3AF) // Cool Grey

    if (isCompleted) {
        // --- COMPLETED STATE (Solid Green + Check) ---
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(successColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "Completed",
                tint = Color.Green,
                modifier = Modifier.size(24.dp)
            )
        }
    } else {
        // --- PENDING STATE (Dashed Border + Text) ---
        Box(
            modifier = Modifier
                .size(size)
                .drawBehind {
                    // Custom drawing for the dashed border
                    val stroke = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(10f, 10f), // 10px On, 10px Off
                            phase = 0f
                        )
                    )

                    drawCircle(
                        color = pendingColor,
                        style = stroke,
                        radius = size.toPx() / 2 - 2.dp.toPx() // Adjust radius so stroke isn't clipped
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayLabel[0].toString(),
                color = pendingColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin
            )
        }
    }
}