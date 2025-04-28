package com.ordresot.diabetessupporter.domain.models

data class Profile(
    val name: String = "",
    val surname: String = "",
    val weight: Double,
    val height: Double
)
