package com.ordresot.diabetessupporter.domain.api.usecase

import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile

interface AuthUseCase {
    fun saveCredentials(credentials: Credentials)
    fun getToken(callback: Callback<Credentials, CredentialsManagerException>)
    fun clearCredentials()
    fun checkCredentials(): Boolean
    fun getUserProfile(callback: Callback<UserProfile, AuthenticationException>)
    fun getAuthBuilder(): WebAuthProvider.Builder
}