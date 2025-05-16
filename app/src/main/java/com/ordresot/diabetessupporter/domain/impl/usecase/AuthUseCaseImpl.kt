package com.ordresot.diabetessupporter.domain.impl.usecase

import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.ordresot.diabetessupporter.domain.api.usecase.AuthUseCase

class AuthUseCaseImpl(
    private val credentialsManager: CredentialsManager,
    private val authBuilder: WebAuthProvider.Builder,
    private val authClient: AuthenticationAPIClient
) : AuthUseCase {
    override fun saveCredentials(credentials: Credentials) {
        credentialsManager.saveCredentials(credentials)
    }

    override fun getToken(callback: Callback<Credentials, CredentialsManagerException>) {
        credentialsManager.getCredentials(callback)
    }

    override fun clearCredentials() {
        credentialsManager.clearCredentials()
    }

    override fun checkCredentials(): Boolean {
        return credentialsManager.hasValidCredentials()
    }

    override fun getUserProfile(callback: Callback<UserProfile, AuthenticationException>) {
        getToken(
            object : Callback<Credentials, CredentialsManagerException> {
                override fun onFailure(error: CredentialsManagerException) {}

                override fun onSuccess(result: Credentials) {
                    authClient.userInfo(result.accessToken).start(callback)
                }
            }
        )
    }

    override fun getAuthBuilder(): WebAuthProvider.Builder {
        return authBuilder
    }

}