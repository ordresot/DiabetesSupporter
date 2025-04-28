package com.redikt.diabetesapp.core.di

import android.content.Context
import com.google.gson.Gson
import com.ordresot.diabetessupporter.data.preference.SharedPrefsClient
import com.ordresot.diabetessupporter.data.repository.PreferencesRepositoryImpl
import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.api.usecase.FirstRunUseCase
import com.ordresot.diabetessupporter.domain.impl.usecase.FirstRunUseCaseImpl

object Creator {
    private lateinit var applicationContext: Context

    fun initContext(context: Context) {
        applicationContext = context.applicationContext
    }

    private fun getPreferencesRepository(): PreferencesRepository = PreferencesRepositoryImpl(
        SharedPrefsClient(
            applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE),
            Gson()
        )
    )

    fun provideFirstRunUseCase(): FirstRunUseCase = FirstRunUseCaseImpl(getPreferencesRepository())
}