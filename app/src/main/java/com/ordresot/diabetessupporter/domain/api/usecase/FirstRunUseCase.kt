package com.ordresot.diabetessupporter.domain.api.usecase

interface FirstRunUseCase {
    fun getFirstRun(consumer: firstRunUseCaseConsumer)
    fun setFirstRun(value: Boolean)

    interface firstRunUseCaseConsumer{
        fun consume(value: Boolean)
    }
}