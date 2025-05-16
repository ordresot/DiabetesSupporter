package com.ordresot.diabetessupporter.data.repository

import androidx.compose.runtime.internal.composableLambdaInstance
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.ordresot.diabetessupporter.data.AuthClient
import com.ordresot.diabetessupporter.domain.api.repository.AuthRepository

class AuthRepositoryImpl(
    private val client: AuthClient
): AuthRepository {
    override fun saveCredentials(credentials: Credentials) {
        client.saveCredentials(credentials)
    }

    override fun getCredentials(callback: Callback<Credentials, CredentialsManagerException>) {
        client.getCredentials(callback)
    }

    override fun clearCredentials() {
        client.clearCredentials()
    }

    override fun checkCredentials(): Boolean {
        return client.checkCredentials()
    }

    override fun getUserProfile(callback: Callback<UserProfile, AuthenticationException>) {
        client.getUserProfile(callback)
    }

    override fun getLoginBuilder(): WebAuthProvider.Builder {
        return client.getLoginBuilder()
    }

    override fun getLogoutBuilder(): WebAuthProvider.LogoutBuilder {
        return client.getLogoutBuilder()
    }
}