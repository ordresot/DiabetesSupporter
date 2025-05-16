package com.ordresot.diabetessupporter.domain.api.repository

import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile

interface AuthRepository {
    fun saveCredentials(credentials: Credentials)
    fun getCredentials(callback: Callback<Credentials, CredentialsManagerException>)
    fun clearCredentials()
    fun checkCredentials(): Boolean
    fun getUserProfile(callback: Callback<UserProfile, AuthenticationException>)
    fun getLoginBuilder(): WebAuthProvider.Builder
    fun getLogoutBuilder(): WebAuthProvider.LogoutBuilder
}