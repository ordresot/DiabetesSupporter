package com.ordresot.diabetessupporter.domain.models

import java.time.LocalDate

data class Profile(
    var firstName: String = "",
    var lastName: String = "",
    var thirdName: String= "",
    var gender: GenderType = GenderType.DEFAULT,
    var birthday: Long?
)
