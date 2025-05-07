package com.ordresot.diabetessupporter.domain.models

import java.time.LocalDate

data class Profile(
    var name: String = "",
    var surname: String = "",
    var gender: GenderType,
    var weight: Double?,
    var height: Double?,
    var birthday: Long?
)
