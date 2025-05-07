package com.ordresot.diabetessupporter.data.dto

data class GlucoseLimitsDto(
    val targetGlucose: Double = 5.6,
    val highGlucose: Double = 8.0,
    val lowGlucose: Double = 4.6,
    val hyperglycemia: Double = 11.0,
    val hypoglycemia: Double = 3.0
)
