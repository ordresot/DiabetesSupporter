package com.ordresot.diabetessupporter.domain.models

import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

data class DiaryEntry(
    val date: LocalDate,
    val time: LocalTime,
    val glucose: Double?,
    val carbs: Double?,
    val fat: Double?,
    val protein: Double?,
    val calories: Double?,
    val medicine: ArrayList<Medicine>?,
    val timestampType: TimestampType
)