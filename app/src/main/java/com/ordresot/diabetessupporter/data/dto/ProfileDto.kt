package com.ordresot.diabetessupporter.data.dto

data class ProfileDto(
    val name: String = "",
    val surname: String = "",
    val weight: Double,
    val height: Double
)