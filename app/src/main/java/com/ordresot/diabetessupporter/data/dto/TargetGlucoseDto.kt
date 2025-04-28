package com.ordresot.diabetessupporter.data.dto

data class TargetGlucoseDto(
    val targetGlucose: Double,
    val highGlucose: Double,
    val lowGlucose: Double,
    val hyperglycemia: Double,
    val hypoglycemia: Double
)
