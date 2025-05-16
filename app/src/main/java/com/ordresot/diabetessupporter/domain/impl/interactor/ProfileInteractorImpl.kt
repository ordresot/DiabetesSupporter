package com.ordresot.diabetessupporter.domain.impl.interactor

import com.ordresot.diabetessupporter.domain.api.interactor.ProfileInteractor
import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.models.GenderType
import java.time.LocalDate

class ProfileInteractorImpl(private val repository: PreferencesRepository): ProfileInteractor {
    override fun getFirstName(): String {
        return repository.getProfile().firstName
    }

    override fun setFirstName(value: String) {
        val profile = repository.getProfile()
        profile.apply {
            firstName = value
        }
        repository.saveProfile(profile)
    }

    override fun getLastName(): String {
        return repository.getProfile().lastName
    }

    override fun setLastName(value: String) {
        val profile = repository.getProfile()
        profile.apply {
            lastName = value
        }
        repository.saveProfile(profile)
    }

    override fun getThirdName(): String {
        return repository.getProfile().thirdName
    }

    override fun setThirdName(value: String) {
        val profile = repository.getProfile()
        profile.apply {
            thirdName = value
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