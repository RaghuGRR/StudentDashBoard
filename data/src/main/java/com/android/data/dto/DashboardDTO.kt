package com.android.data.dto

import com.google.gson.annotations.SerializedName

data class DashboardDto(
    @SerializedName("student") val student: StudentDto,
    @SerializedName("todaySummary") val todaySummary: TodaySummaryDto,
    @SerializedName("weeklyOverview") val weeklyOverview: WeeklyOverviewDto
)

data class StudentDto(
    val name: String,
    @SerializedName("class") val studentClass: String,
    val availability: AvailabilityDto,
    val quiz: QuizStatsDto,
    val accuracy: AccuracyDto
)

data class AvailabilityDto(val status: String)
data class QuizStatsDto(val attempts: Int)
data class AccuracyDto(val current: String)


data class TodaySummaryDto(
    val mood: String,
    val description: String,
    val recommendedVideo: VideoDto,
    val characterImage: String?
)

data class VideoDto(
    val title: String,
    val actionText: String
)


data class WeeklyOverviewDto(
    val quizStreak: List<StreakDto>,
    val overallAccuracy: OverallAccuracyDto,
    val performanceByTopic: List<TopicDto>
)

data class StreakDto(
    val day: String,
    val status: String
)

data class OverallAccuracyDto(
    val percentage: Int,
    val label: String
)

data class TopicDto(
    val topic: String,
    val trend: String
)