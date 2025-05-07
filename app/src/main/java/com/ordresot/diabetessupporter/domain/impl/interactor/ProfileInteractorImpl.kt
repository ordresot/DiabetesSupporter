package com.ordresot.diabetessupporter.domain.impl.interactor

import com.ordresot.diabetessupporter.domain.api.interactor.ProfileInteractor
import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.models.GenderType
import java.time.LocalDate

class ProfileInteractorImpl(private val repository: PreferencesRepository): ProfileInteractor {
    override fun getName(): String {
        return repository.getProfile().name
    }

    override fun setName(value: String) {
        val profile = repository.getProfile()
        profile.apply {
            name = value
        }
        repository.saveProfile(profile)
    }

    override fun getSurname(): String {
        return repository.getProfile().surname
    }

    override fun setSurname(value: String) {
        val profile = repository.getProfile()
        profile.apply {
            surname = value
        }
        repository.saveProfile(profile)
    }

    override fun getWeight(): Double? {
        return repository.getProfile().weight
    }

    override fun setWeight(value: Double) {
        val profile = repository.getProfile()
        profile.apply {
            weight = value
        }
        repository.saveProfile(profile)
    }

    override fun getHeight(): Double? {
        return repository.getProfile().height
    }

    override fun setHeight(value: Double) {
        val profile = repository.getProfile()
        profile.apply {
            height = value
        }
        repository.saveProfile(profile)
    }

    override fun getBirthday(): Long? {
        return repository.getProfile().birthday
    }

    override fun setBirthday(value: Long) {
        val profile = repository.getProfile()
        profile.apply {
            birthday = value
        }
        repository.saveProfile(profile)
    }

    override fun getGender(): GenderType {
        return repository.getProfile().gender
    }

    override fun setGender(value: GenderType) {
        val profile = repository.getProfile()
        profile.apply {
            gender = value
        }
        repository.saveProfile(profile)
    }

}