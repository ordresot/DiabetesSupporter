package com.ordresot.diabetessupporter.data.dto

import com.ordresot.diabetessupporter.domain.models.GenderType

data class ProfileDto(
    val firstName: String = "",
    val lastName: String = "",
    val thirdName: String= "",
    val gender: GenderType = GenderType.DEFAULT,
    val birthday: Long? = null
)