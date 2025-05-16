package com.ordresot.diabetessupporter.domain.api.interactor

import com.ordresot.diabetessupporter.domain.models.GenderType


interface ProfileInteractor {
    fun getFirstName(): String
    fun setFirstName(value: String)

    fun getLastName(): String
    fun setLastName(value: String)

    fun getThirdName(): String
    fun setThirdName(value: String)

    fun getBirthday(): Long?
    fun setBirthday(value: Long)

    fun getGender(): GenderType
    fun setGender(value: GenderType)
}