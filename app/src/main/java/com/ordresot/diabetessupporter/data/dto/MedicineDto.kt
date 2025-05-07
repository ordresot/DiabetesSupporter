package com.ordresot.diabetessupporter.data.dto

import com.ordresot.diabetessupporter.domain.models.AmountType

data class MedicineDto(
    val name: String,
    val value: Int,
    val type: AmountType
)