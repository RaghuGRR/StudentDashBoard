package com.android.domain.model


data class DashboardModel(
    val studentProfile: StudentProfileModel?,
    val dailyInsight: DailyInsightModel?,
    val weeklyPerformance: WeeklyPerformanceModel?
)


data class StudentProfileModel(
    val name: String,
    val standard: String,
    val availabilityStatus: String,
    val quizAttempts: Int,
    val accuracyPercentage: String
)


data class DailyInsightModel(
    val mood: String,
    val description: String,
    val videoRecommendation: VideoRecommendationModel,
    val characterImageUrl: String?
)

data class VideoRecommendationModel(
    val title: String,
    val buttonText: String
)


data class WeeklyPerformanceModel(
    val overallScore: Int,
    val scoreLabel: String,
    val streak: List<DayStreakModel>,
    val topicTrends: List<TopicTrendModel>
)

data class DayStreakModel(
    val dayLabel: String,
    val isCompleted: Boolean
)

data class TopicTrendModel(
    val topicName: String,
    val isTrendingUp: Boolean
)