package com.ordresot.diabetessupporter.data.dto

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime

data class GlucoseMeasureDto(
    val result: Double,
    val measuredAt: OffsetDateTime,
    val circumstances: CircumstancesDto
) {
}