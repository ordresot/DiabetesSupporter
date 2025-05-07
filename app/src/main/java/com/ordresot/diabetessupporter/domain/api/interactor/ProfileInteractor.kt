package com.ordresot.diabetessupporter.domain.api.interactor

import com.ordresot.diabetessupporter.domain.models.GenderType
import java.time.LocalDate


interface ProfileInteractor {
    fun getName(): String
    fun setName(value: String)

    fun getSurname(): String
    fun setSurname(value: String)

    fun getWeight(): Double?
    fun setWeight(value: Double)

    fun getHeight(): Double?
    fun setHeight(value: Double)

    fun getBirthday(): Long?
    fun setBirthday(value: Long)

    fun getGender(): GenderType
    fun setGender(value: GenderType)
}