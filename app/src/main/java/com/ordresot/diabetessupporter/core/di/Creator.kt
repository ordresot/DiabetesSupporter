package com.ordresot.diabetessupporter.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.auth0.android.provider.WebAuthProvider
import com.google.gson.Gson
import com.ordresot.diabetessupporter.data.network.DiabetesApiService
import com.ordresot.diabetessupporter.data.network.RetrofitNetworkClient
import com.ordresot.diabetessupporter.data.preference.SharedPrefsClient
import com.ordresot.diabetessupporter.data.repository.PreferencesRepositoryImpl
import com.ordresot.diabetessupporter.data.repository.RemoteDataRepositoryImpl
import com.ordresot.diabetessupporter.domain.api.interactor.GlucoseLimitsInteractor
import com.ordresot.diabetessupporter.domain.api.interactor.ProfileInteractor
import com.ordresot.diabetessupporter.domain.api.repository.PreferencesRepository
import com.ordresot.diabetessupporter.domain.api.repository.RemoteDataRepository
import com.ordresot.diabetessupporter.domain.api.usecase.FirstRunUseCase
import com.ordresot.diabetessupporter.domain.api.usecase.GlucoseMeasurementUseCase
import com.ordresot.diabetessupporter.domain.api.usecase.AuthUseCase
import com.ordresot.diabetessupporter.domain.impl.interactor.GlucoseLimitsLimitsInteractorImpl
import com.ordresot.diabetessupporter.domain.impl.interactor.ProfileInteractorImpl
import com.ordresot.diabetessupporter.domain.impl.usecase.FirstRunUseCaseImpl
import com.ordresot.diabetessupporter.domain.impl.usecase.GlucoseMeasurementUseCaseImpl
import com.ordresot.diabetessupporter.domain.impl.usecase.AuthUseCaseImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Creator {
    private lateinit var applicationContext: Context

    fun initContext(context: Context) {
        applicationContext = context.applicationContext
    }

    private fun getAuth0Account(): Auth0 = Auth0.getInstance(applicationContext)

    private fun getAuthenticationClient(): AuthenticationAPIClient = AuthenticationAPIClient(
        getAuth0Account()
    )

    private fun getPreferencesRepository(): PreferencesRepository = PreferencesRepositoryImpl(
        SharedPrefsClient(
            applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE),
            Gson()
        )
    )

    private fun getAuthManager(): CredentialsManager = CredentialsManager(
        getAuthenticationClient(),
        SharedPreferencesStorage(applicationContext)
    )

    private fun getRemoteDataRepository(): RemoteDataRepository = RemoteDataRepositoryImpl(
        RetrofitNetworkClient(
            Retrofit.Builder()
                .baseUrl("http://localhost:10000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DiabetesApiService::class.java),
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    )

    fun provideFirstRunUseCase(): FirstRunUseCase = FirstRunUseCaseImpl(
        getPreferencesRepository()
    )

    fun provideGlucoseMeasurementUseCase(): GlucoseMeasurementUseCase = GlucoseMeasurementUseCaseImpl(
        getPreferencesRepository()
    )

    fun provideProfileInteractor(): ProfileInteractor = ProfileInteractorImpl(
        getPreferencesRepository()
    )

    fun provideGlucoseLimitsInteractor(): GlucoseLimitsInteractor = GlucoseLimitsLimitsInteractorImpl(
        getPreferencesRepository()
    )

    fun provideAuthUseCase(): AuthUseCase = AuthUseCaseImpl(
        getAuthManager(),
        WebAuthProvider.login(getAuth0Account())
            .withScheme("demo")
            .withScope("openid email profile offline_access"),
        getAuthenticationClient()
    )
}