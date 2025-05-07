package com.ordresot.diabetessupporter.domain.models

data class GlucoseLimits(
    var targetGlucose: Double,
    var highGlucose: Double,
    var lowGlucose: Double,
    var hyperglycemia: Double,
    var hypoglycemia: Double
)