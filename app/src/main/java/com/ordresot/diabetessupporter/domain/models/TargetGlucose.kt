package com.ordresot.diabetessupporter.domain.models

data class TargetGlucose(
    val targetGlucose: Double,
    val highGlucose: Double,
    val lowGlucose: Double,
    val hyperglycemia: Double,
    val hypoglycemia: Double
)