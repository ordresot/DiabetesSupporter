package com.ordresot.diabetessupporter.data.dto

import com.ordresot.diabetessupporter.domain.models.GenderType

data class ProfileDto(
    val name: String = "",
    val surname: String = "",
    val gender: GenderType = GenderType.DEFAULT,
    val weight: Double? = null,
    val height: Double? = null,
    val birthday: Long? = null
)