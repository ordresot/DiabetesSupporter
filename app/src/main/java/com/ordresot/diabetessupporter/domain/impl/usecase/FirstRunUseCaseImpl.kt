package com.ordresot.diabetessupporter.domain.impl.usecase

import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.api.usecase.FirstRunUseCase

class FirstRunUseCaseImpl(private val repository: PreferencesRepository): FirstRunUseCase {
    override fun getFirstRun(consumer: FirstRunUseCase.firstRunUseCaseConsumer) {
        consumer.consume(repository.getFirstRun())
    }

    override fun setFirstRun(value: Boolean) {
        repository.saveFirstRun(value)
    }
}