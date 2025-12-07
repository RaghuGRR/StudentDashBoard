package com.android.data.mappers

import com.android.data.dto.DashboardDto
import com.android.data.dto.StudentDto
import com.android.data.dto.TodaySummaryDto
import com.android.data.dto.WeeklyOverviewDto
import com.android.domain.model.DailyInsightModel
import com.android.domain.model.DashboardModel
import com.android.domain.model.DayStreakModel
import com.android.domain.model.StudentProfileModel
import com.android.domain.model.TopicTrendModel
import com.android.domain.model.VideoRecommendationModel
import com.android.domain.model.WeeklyPerformanceModel

fun DashboardDto?.toDomain(): DashboardModel{
    return DashboardModel(
        studentProfile = this?.student?.toDomain(),
        dailyInsight = this?.todaySummary?.toDomain(),
        weeklyPerformance = this?.weeklyOverview?.toDomain()
    )
}

private fun StudentDto.toDomain(): StudentProfileModel {
    return StudentProfileModel(
        name = this.name,
        standard = this.studentClass,
        availabilityStatus = this.availability.status,
        quizAttempts = this.quiz.attempts,
        accuracyPercentage = this.accuracy.current
    )
}

private fun TodaySummaryDto.toDomain(): DailyInsightModel{
    return DailyInsightModel(
        mood = this.mood,
        description = this.description,
        characterImageUrl = this.characterImage,
        videoRecommendation = VideoRecommendationModel(
            title = this.recommendedVideo.title,
            buttonText = this.recommendedVideo.actionText
        )
    )
}

private fun WeeklyOverviewDto.toDomain(): WeeklyPerformanceModel{
    return WeeklyPerformanceModel(
        overallScore = this.overallAccuracy.percentage,
        scoreLabel = this.overallAccuracy.label,

        streak = this.quizStreak.map { dto ->
            DayStreakModel(
                dayLabel = dto.day,
                isCompleted = dto.status.equals("done", ignoreCase = true)
            )
        },

        topicTrends = this.performanceByTopic.map { dto ->
            TopicTrendModel(
                topicName = dto.topic,
                isTrendingUp = dto.trend.equals("up", ignoreCase = true)
            )
        }
    )
}